package lesson_6_Work_with_network.consoleСhatTask;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class MessageIn {

    public MessageIn (Socket socket, DataInputStream in){
        try {
            while (!socket.isClosed()) {
                String msg = in.readUTF();
                System.out.println(msg);
                if (msg.equalsIgnoreCase("/q")){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

