/**
 * Обработчик клиента
 * В классе описана авторизация пользователя со стороны сервера,
 * а так же работа с клиентом
 */
package lesson_8_NetworkChat_part_2.Server;

import lesson_8_NetworkChat_part_2.TimeMeter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler {

    Socket socket = null;
    DataInputStream in;
    DataOutputStream out;
    Server server;
    ArrayList<String> blackList;

    public String getNick() {
        return nick;
    }

    String nick;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.blackList = new ArrayList<>();

            new Thread(new Runnable() {                                     //Работаем с каждым клиентом в отдельном потоке
                @Override
                public void run() {
                    try {

                        clientAuthorization();                              //Авторизация клиента
                        workWithClient();                                       //Работа с клиентом

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
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        server.unsubscribe(ClientHandler.this);     //Убираем клиента из списка подключенных
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
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
     * Авторизация клиента и проверка корректности введенного логина и пароля
     * @throws IOException
     */
    public void clientAuthorization() throws IOException {

        while (true) {

            String str = in.readUTF();
            if(str.startsWith("/auth")) {
                String[] tokens = str.split(" ");
                String newNick = AuthService.getNickByLoginAndPass(tokens[1], tokens[2]);
                if(newNick != null) {
                    if(!server.isNickBusy(newNick)) {
                        sendMsg("/authok");
                        nick = newNick;
                        server.subscribe(ClientHandler.this);
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

    public void workWithClient () throws IOException {
        while (true) {
            String str = in.readUTF();
            if(str.startsWith("/")) {
                if(str.equals("/end")) {
                    out.writeUTF("/serverClosed");
                }
                if(str.startsWith("/w ")) {
                    String[] tokens = str.split(" ",3);
                    server.sendPersonalMsg(ClientHandler.this, tokens[1], tokens[2]);
                }
                if(str.startsWith("/blacklist ")) {
                    String[] tokens = str.split(" ");
                    blackList.add(tokens[1]);
                    sendMsg("Вы добавили пользователя " + tokens[1] + " в черный список");
                }
            } else {
                server.broadcastMsg(ClientHandler.this,nick + ": " + str);
            }
        }
    }
}
