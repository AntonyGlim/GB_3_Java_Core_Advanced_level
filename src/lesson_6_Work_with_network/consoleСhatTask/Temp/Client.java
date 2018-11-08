package lesson_6_Work_with_network.consoleСhatTask.Temp;

import lesson_6_Work_with_network.consoleСhatTask.Constants;

import java.io.*;
import java.net.Socket;

public class Client {

    private Socket socket;              //Для соединения
    private BufferedReader br;          //Для чтения из консоли
    private DataInputStream in;         //Для чтения извне
    private DataOutputStream out;       //Для отправки наружу
    private SendMessage sendMessage;
    private Thread sendThread;
    private ReceiveMessage receiveMessage;  //Получить письмо
    private Thread receiveThread;

    Client() throws IOException {

        socket = new Socket(Constants.IPADRESS, Constants.PORT);
        br = new BufferedReader(new InputStreamReader(System.in)) ;
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        sendMessage = new SendMessage(br, out, socket);
        sendThread = new Thread(sendMessage);

        receiveMessage = new ReceiveMessage(in, socket);
        receiveThread = new Thread(receiveMessage);

        sendThread.start();
        receiveThread.setDaemon(true);
        receiveThread.start();

        try {
            sendThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        in.close();
        out.close();
        socket.close();
        System.out.println("Соединение прервано.");
    }
}

