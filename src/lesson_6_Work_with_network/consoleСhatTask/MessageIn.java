package lesson_6_Work_with_network.consoleСhatTask;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Класс хранит метод получения информации извне
 */
public class MessageIn {

    public MessageIn (DataInputStream in) throws IOException {
            while (true) {
                String msg = in.readUTF();
                System.out.println(msg);
                if (msg.equalsIgnoreCase("/q")) System.exit(0);
            }
    }
}

