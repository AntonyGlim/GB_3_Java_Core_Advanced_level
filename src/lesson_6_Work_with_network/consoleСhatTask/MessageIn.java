package lesson_6_Work_with_network.consoleСhatTask;

import java.io.DataInputStream;
import java.io.IOException;

public class MessageIn {

    public MessageIn (DataInputStream in) throws IOException {
            while (true) {
                String msg = in.readUTF();
                System.out.println(msg);
                if (msg.equalsIgnoreCase("/q")) break;
            }
    }
}

