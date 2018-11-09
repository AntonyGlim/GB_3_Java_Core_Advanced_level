package lesson_6_Work_with_network.consoleСhatTask;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class InformationGetting  implements Runnable{

    private Socket socket;
    private DataInputStream in;


    public InformationGetting(Socket socket) throws IOException {
        this.socket = socket;
        in = new DataInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        try {
            while (true) {                                                  //Получение сообщений
                String msg = in.readUTF();
                System.out.println(msg);
                if (msg.equalsIgnoreCase("/q")) System.exit(0);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in.close();
            socket.close();
            System.out.println("Соединение прервано (from ClientIn)");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}