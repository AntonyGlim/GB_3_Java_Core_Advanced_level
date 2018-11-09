package lesson_6_Work_with_network.consoleСhatTask.server;

import lesson_6_Work_with_network.consoleСhatTask.MessageIn;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Класс предназначен для получения информации извне
 */
public class ServerIn  implements Runnable{

    private Socket client;
    private DataInputStream in;


    public ServerIn(Socket socket) throws IOException {
        this.client = socket;
        in = new DataInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        try {
            new MessageIn(in);                                              //Вызываем метод отправки сообщений

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in.close();
            client.close();
            System.out.println("Соединение прервано (from ServerIn)");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
