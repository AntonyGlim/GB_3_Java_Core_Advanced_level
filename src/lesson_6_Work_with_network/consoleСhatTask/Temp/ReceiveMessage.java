package lesson_6_Work_with_network.consoleСhatTask.Temp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ReceiveMessage implements Runnable {

    private DataInputStream in;         //Для чтения извне
    private Socket socket;

    ReceiveMessage(DataInputStream in, Socket socket){
        this.in = in;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!socket.isOutputShutdown()) {
                System.out.println(in.readUTF());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
