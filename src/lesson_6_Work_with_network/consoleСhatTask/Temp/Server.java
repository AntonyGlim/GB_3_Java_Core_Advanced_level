package lesson_6_Work_with_network.consoleСhatTask.Temp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static lesson_6_Work_with_network.consoleСhatTask.Constants.PORT;

public class Server {

    private ServerSocket serverSocket;  //Серверный сокет
    private Socket client;              //Для соединения
    private BufferedReader br;          //Для чтения из консоли
    private DataInputStream in;         //Для чтения извне
    private DataOutputStream out;       //Для отправки наружу

    Server() throws IOException {
        serverSocket = new ServerSocket(PORT);
        System.out.println("Сервер ожидает подключения пользователя...");
        client = serverSocket.accept();

        br = new BufferedReader(new InputStreamReader(System.in)) ;
        in = new DataInputStream(client.getInputStream());
        out = new DataOutputStream(client.getOutputStream());

        while (!client.isClosed()){
            String msgFromClient = in.readUTF();
            if (msgFromClient.equalsIgnoreCase("/q")) break;
            System.out.println(msgFromClient);

        }
    }
}
