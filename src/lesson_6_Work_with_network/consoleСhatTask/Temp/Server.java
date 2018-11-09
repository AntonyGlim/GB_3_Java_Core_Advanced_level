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

        while (true){
            String msgFromClient = in.readUTF();
            System.out.println(msgFromClient);
            out.writeUTF("User send: " + msgFromClient);
            if (msgFromClient.equalsIgnoreCase("/q")) {
                break;
            }
        }
        try {
            br.close();
            out.close();
            in.close();
            client.close();
            serverSocket.close();
            System.out.println("Соединение прервано (from Server)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

