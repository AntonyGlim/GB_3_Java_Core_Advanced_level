/**
 * Класс постоянно ожидает подключения новых клиентов,
 * и если подключение происходит - создает нового клиента,
 * и добавляет его в перечень подключенных клиентов.
 *
 * Содержит в себе методы по работе со списком клиентов,
 * методы отправки сообщений клиентам, проверку задвоенности ников.
 */
package lesson_8_NetworkChat_part_2.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;

import static lesson_8_NetworkChat_part_2.Constants.PORT;

public class Server {

    private Vector<ClientHandler> clients;                              //Создаем список подключенных клиентов. Vector - чтобы обеспечить синхронизацию доступа

    public Server() throws SQLException {
        clients = new Vector<>();
        ServerSocket server = null;                                     //Инициализируем ServerSocket
        Socket socket = null;                                           //Инициализируем Socket
        try {
            AuthService.connect();                                      //Устанавливаем соединение с БД

//          Блок в котором мы костыльно добавляли пользователь в БД
            AuthService.addUser("l1", "p1", "nick1");
            AuthService.addUser("l2", "p2", "nick2");
            AuthService.addUser("l3", "p3", "nick3");
            AuthService.addUser("l4", "p4", "nick4");
            AuthService.addUser("l5", "p5", "nick5");
            AuthService.addUser("l6", "p6", "nick6");
            AuthService.addUser("l7", "p7", "nick7");
            AuthService.addUser("l8", "p8", "nick8");
            AuthService.addUser("l9", "p9", "nick9");

            server = new ServerSocket(PORT);                            //Берем константу
            System.out.println("Сервер запущен!");

            while (true) {
                socket = server.accept();                               //Ждем клиента
                System.out.println("Клиент подключился");
                new ClientHandler(this, socket);                     //Создаем новый класс для работы с клиентом
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

    /**
     * Метод добавляет нового клиента в список
     * и рассылает список подключенных пользователей всем клиентам
     * @param client
     */
    public void subscribe(ClientHandler client) {
        clients.add(client);
        broadcastClientList();
    }

    /**
     * Метод удаляет клиента из списока
     * и рассылает список подключенных пользователей всем клиентам
     * @param client
     */
    public void unsubscribe(ClientHandler client) {
        clients.remove(client);
        broadcastClientList();
    }

    /**
     * Метод рассылает сообщение от одного, всем пользователям
     * из списка подключенных польхователей,
     * кроме тех, у кого он есть в списке заблокированных пользователей
     * @param from - клиент, который хочет послать сообщение
     * @param msg - сообщение
     */
    public void broadcastMsg(ClientHandler from, String msg) {
        for (ClientHandler o: clients) {
            if(!o.checkBlackList(from.getNick())) {                                 //Если тот, кто хочет послать сообщение не в черном списке конкретного клиента
                o.sendMsgWithDate(msg);                                                     //то он может послать ему сообщение
            }
        }
    }

    /**
     * Метод проверяет, имеется-ли такой ник в БД
     * @param nick - соответствует введенным пользователем логину и паролю
     * @return - да - ник занят / нет - ник свободен
     */
    public boolean isNickBusy(String nick) {
        for (ClientHandler o: clients) {
            if(o.getNick().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Метод отправляет личные сообщения от одного пользователя
     * конкретному другому пользователю
     * @param from - от кого
     * @param nickTo - кому (по нику)
     * @param msg - сообщение
     */
    public void sendPersonalMsg(ClientHandler from, String nickTo, String msg) {
        for (ClientHandler o: clients) {
            if(o.getNick().equals(nickTo)) {                                        //Если в списке есть вызываемый ник
                o.sendMsgWithDate("from " + from.getNick() + ": " + msg);
                from.sendMsgWithDate("to " + nickTo + ": " + msg);
                return;
            }
        }
        from.sendMsgWithDate("Клиент с ником " + nickTo + " не найден!");
    }

    /**
     * Метод рассылает список подключенных пользователей всем клиентам
     */
    public void broadcastClientList() {
        StringBuilder sb = new StringBuilder();
        sb.append("/clientlist ");
        for (ClientHandler o : clients) {                                           //Формируем строку для рассылки
            sb.append(o.getNick() + " ");
        }
        String out = sb.toString();                                                 //Рассылаем всем
        for (ClientHandler o : clients) {
            o.sendMsg(out);
        }
    }
}
