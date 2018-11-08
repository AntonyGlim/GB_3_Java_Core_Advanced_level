package lesson_6_Work_with_network.consoleСhatTask.Temp;

import lesson_6_Work_with_network.consoleСhatTask.Constants;

import java.io.*;
import java.net.Socket;

public class Client {

    private Socket socket;              //Для соединения
    private BufferedReader br;          //Для чтения из консоли
    private DataInputStream in;         //Для чтения извне
    private DataOutputStream out;       //Для отправки наружу

    Client() throws IOException {

        socket = new Socket(Constants.IPADRESS, Constants.PORT);
        br = new BufferedReader(new InputStreamReader(System.in)) ;
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        while (!socket.isOutputShutdown()){
            if(br.ready()){
                String clientMsg = br.readLine();
                out.writeUTF(clientMsg);
                out.flush();
                if(clientMsg.equalsIgnoreCase("/q")) break;
            }
        }

        in.close();
        out.close();
        socket.close();
        System.out.println("Соединение прервано.");
    }
}
