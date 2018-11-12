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
//TODO СДЕЛАТЬ ВЕКТОР 2 МЕРНЫМ ПЕРВЫЙ ТИП - ClientHandler а второй String будет хранить Nick
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

    //TODO метод для отправки шепота конкретному клиенту
    public void whisperTo (String nick, String msg, ClientHandler clientHandler){
        //TODO найти конкретного пользователя по нику и отправить ему сообщение
        for (ClientHandler o: clients) {
            String userNick = o.getNick();
            if(userNick.equals(nick)){
                o.sendMsg(clientHandler.getNick() + ": private to you: " + msg);
                clientHandler.sendMsg(clientHandler.getNick() + ": private to " + nick + ": " + msg);
                break;
            }
        }
    }

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
