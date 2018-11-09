package lesson_6_Work_with_network.consoleСhatTask.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static lesson_6_Work_with_network.consoleСhatTask.Constants.PORT;

public class ServerMain {

    private ServerSocket serverSocket;  //Серверный сокет
    private Socket client;              //Для соединения

    ServerMain() throws IOException, InterruptedException {

        serverSocket = new ServerSocket(PORT);
        System.out.println("Сервер ожидает подключения пользователя...");
        client = serverSocket.accept();
        System.out.println("Пользователь подключился.");


        Thread threadOut = new Thread(new ServerOut(client));
        Thread threadIn = new Thread(new ServerIn(client));

        threadIn.start();
        threadOut.start();

        threadIn.join();
        threadOut.join();

        System.out.println("Соединение прервано (from ServerMain)");
//        client.close();
        serverSocket.close();

    }
}
