/**
 * Обработчик клиента
 * В классе описана авторизация пользователя со стороны сервера,
 * а так же работа с клиентом
 */
package lesson_8_NetworkChat_part_2.Server;

import lesson_8_NetworkChat_part_2.TimeNow;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class ClientHandler {

    private Socket socket = null;
    private DataInputStream in;
    private DataOutputStream out;
    private Server server;
    private ArrayList<String> blackList;
    private boolean isAuthorized = false;
    private String nick;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.blackList = new ArrayList<>();                             //TODO считать БД в лист

            new Thread(new Runnable() {                                     //Работаем с каждым клиентом в отдельном потоке
                @Override
                public void run() {
                    try {
                        clientAuthorization();                              //Авторизация клиента
                        if(isAuthorized) {
                            workWithClient();                               //Работа с клиентом
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {                                             //Закрываем все потоки и сокет
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            socket.close();
                            System.out.println("Соединение с клиентом потеряно");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (isAuthorized) {
                            server.unsubscribe(ClientHandler.this);     //Убираем клиента из списка подключенных
                        }
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * getNick
     * @return
     */
    public String getNick() {
        return nick;
    }

    /**
     * Метод укажет помещел-ли данный ник в черный список
     * @param nick - который нужно проверить
     * @return - да, если nick присутствует в списке
     */
    public boolean checkBlackList(String nick) {
        return blackList.contains(nick);
    }

    /**
     * Метод посылает сообщение одному пользователю
     * @param msg - сообщение
     */
    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод посылает сообщение одному пользователю включая в него дату
     * @param msg - сообщение
     */
    public void sendMsgWithDate(String msg) {
        TimeNow timeNow = new TimeNow();
        String date = timeNow.TimeNow();
        try {
            out.writeUTF(date + msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Авторизация клиента и проверка корректности введенного логина и пароля
     * @throws IOException
     */
    public void clientAuthorization() throws IOException {

        while (true) {
            String str = in.readUTF();
//            timeCount();
            if (str.startsWith("/timeLimit")){
                System.out.println("Отключили клиента по времени");
                break;
            }
            if(str.startsWith("/auth")) {
                String[] tokens = str.split(" ");
                String newNick = AuthService.getNickByLoginAndPass(tokens[1], tokens[2]);
                if(newNick != null) {
                    if(!server.isNickBusy(newNick)) {
                        sendMsg("/authok");
                        nick = newNick;
                        server.subscribe(ClientHandler.this);
                        isAuthorized = true;
                        break;
                    } else {
                        sendMsg("Учетная запись уже используется!");
                    }
                } else {
                    sendMsg("Неверный логин/пароль!");
                }
            }
        }
    }

    /**
     * Непосредственно, работа с клиентом
     * Работает с ключевыми словами:
     * "/end" - Будет произведено завершение работы с сервером
     * "/serverClosed" - Передаем сообщение на сервер, чтобы он завершил свою работу
     * "/w " - Сообщене будет отправлено конкретному пользователю
     * "/blacklist " - Добавление пользователя в черный список
     * @throws IOException
     */
    public void workWithClient () throws IOException {
        //TODO записать в лист массив из черного списка
        blackListStringInArrayList(blackList, this.nick);

        while (true) {
            String str = in.readUTF();
            if(str.startsWith("/")) {
                if(str.equals("/end")) {
                    out.writeUTF("/serverClosed");
                    break;
                }
                if(str.startsWith("/w ")) {
                    String[] tokens = str.split(" ",3);
                    server.sendPersonalMsg(ClientHandler.this, tokens[1], tokens[2]);
                }
                if(str.startsWith("/blacklist ")) {                                             //Если от клиента приходит ник для черного списка
                    String[] tokens = str.split(" ");
                    AuthService.addUserInBlackList(this.nick, tokens[1]);
                    blackListStringInArrayList(blackList, this.nick);                                                   //TODO считать БД в лист (обновить лист)
                    sendMsg("Вы добавили пользователя " + tokens[1] + " в черный список");
                }
                if(str.startsWith("/seeblacklist")) {
                    sendMsg("Ваш черный список в БД включает: " + AuthService.getBlackListStringFromDB(this.nick));
                }
                if(str.startsWith("/printlist")) {
                    sendMsg("Ваш черный список в листе включает: " + blackList);
                }
                if(str.startsWith("/delfrombl")) {                                             //Если от клиента приходит ник для удаления из черного списка
                    String[] tokens = str.split(" ", 2);
                    AuthService.deleteUserFromBlackList(this.nick, tokens[1]);
                    blackListStringInArrayList(blackList, this.nick);
                    sendMsg("Вы удалили пользователя " + tokens[1] + " из черного списка");
                }
            } else {
                server.broadcastMsg(ClientHandler.this,nick + ": " + str);
            }
        }
    }

    public static void blackListStringInArrayList(ArrayList<String> blackList, String nick){
        String blackListString = AuthService.getBlackListStringFromDB(nick);
        String[] tokens = blackListString.split(" ");
        blackList.clear();
        Collections.addAll(blackList, tokens);
    }

//    /**
//     * Метод засекает время в отдельном потоке
//     * когда лимит времени превысит определенное число секунд -
//     * метод отправит команду для завершения клиента
//     */
//    public void timeCount(){
//        Thread timer = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                long timeFromStart = 0L;
//                while (true){
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    timeFromStart++;
//                    if (timeFromStart == 120); {
//                        sendMsg("Время ожидания превышено");
//                        sendMsg("/timeLimit");
//                        break;
//                    }
//                }
//            }
//        });
//        timer.start();
//    }


}
