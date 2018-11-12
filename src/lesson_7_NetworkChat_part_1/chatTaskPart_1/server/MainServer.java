package lesson_7_NetworkChat_part_1.chatTaskPart_1.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;

public class MainServer {
    Vector<ClientHandler> clients;

    public MainServer() throws SQLException {

        ServerSocket server = null;
        Socket socket = null;
        clients = new Vector<>();

        try {
            AuthService.connect();

            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");

            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился!");
                // создаем нового клиента
               new ClientHandler(this, socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AuthService.disconnect();
        }

    }
    // метод для рассылки сообщения всем клиентам
    public void broadCastMsg(String msg) {
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }
    }

    /**
     * Метод для отправки шепота(приватного сообщения) конкретному клиенту
     * На стороне отправившего и получившего будут разные сообщения.
     * @param nick - клиент, которомы необходимо отправить приватное сообщение
     * @param msg - сообщение (без ключевого слова)
     * @param clientHandler - экземпляр класса, для получения ника того, кто посылает сообщения
     */
    public void whisperTo (String nick, String msg, ClientHandler clientHandler){

        for (ClientHandler o : clients) {
            String userNick = o.getNick();
            if(userNick.equals(nick)){                                                                  //Если в списках пользователей такой ник есть
                o.sendMsg(clientHandler.getNick() + ": private to you: " + msg);
                clientHandler.sendMsg(clientHandler.getNick() + ": private to " + nick + ": " + msg);
                break;
            }
        }
    }

    /**
     * Метод передает нформацию занят-ли предполагаемый ник
     * @param newNick - ник, который нужно проверить
     * @return boolean да - этот ник свободен / нет - ник занят
     */
    public boolean isNickEmpty (String newNick){
        boolean result = true;
        for (ClientHandler o : clients){
            if (newNick.equals(o.getNick())){
                result = false;
                break;
            }
        }
        return result;
    }

    // подписываем клиента и добавляем его в список клиентов
    public void subscribe(ClientHandler client) {
        clients.add(client);
    }

    // отписываем клиента и удаляем его из списка клиентов
    public void unsubscribe(ClientHandler client) {
        clients.remove(client);
    }

}
