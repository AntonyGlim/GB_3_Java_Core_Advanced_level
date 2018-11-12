package lesson_7_NetworkChat_part_1.chatTaskPart_1.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

// класс для работы к клиентами
public class ClientHandler {

    private MainServer server;

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private String nick;

    public ClientHandler(MainServer server, Socket socket) {

        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // цикл для авторизации
                        while (true) {
                            String str = in.readUTF();
                            // если приходит сообщение начинающееся с /auth значит пользователь хочет авторизоваться
                            if(str.startsWith("/auth")) {
                                String[] tokens = str.split(" ");
                                // запрашиваем ник в БД
                                String newNick = AuthService.getNickByLoginAndPass(tokens[1], tokens[2]);
                               // если ответ не равен null отправляем ответ клиенту о том, что авторизация прошла успешно
                                if(newNick != null) {  //TODO добавить здесь проверку на одинаковые ники
                                    if (server.isNickEmpty(newNick)){
                                        sendMsg("/authok");
                                        nick = newNick;
                                        server.subscribe(ClientHandler.this);
                                        break;
                                    } else {
                                        sendMsg("Такой пользователь уже присутствует в сети!");
                                    }
                                } else {
                                    sendMsg("Неверный логин/пароль!");
                                }
                            }
                        }
                        // цикл для работы
                        while (true) {
                            String str = in.readUTF();
                            if(str.equals("/end")) {
                                out.writeUTF("/serverClosed");
                                break;
                            }
                            if (str.startsWith ("/w")){                     //Если сообщение приватное
                                String[] whisper = str.split(" ");
                                String userNick = whisper[1];

                                StringBuffer result = new StringBuffer();       //Преобразовать массив обратно в String и отсечь все лишнее
                                for (int i = 2; i < whisper.length; i++) {      //Начинаем с третьего элемента массива, т.к. первые 2 это ключевое слово и ник
                                    result.append(whisper[i] + " ");
                                }
                                String msg = result.toString();

                                server.whisperTo (userNick, msg, ClientHandler.this);  //Посылаем приватное сообщение

                            } else {
                                server.broadCastMsg(nick + ": " + str);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
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
                        server.unsubscribe(ClientHandler.this);
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // метод для оправки сообщения 1 клиенту
    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Добавили метод, чтобы работать с ником Клиента
     * @return
     */
    public String getNick(){
        return nick;
    }
}
