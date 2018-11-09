package lesson_6_Work_with_network.consoleСhatTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

public class MessageOut {

    public MessageOut(BufferedReader br, DataOutputStream out) throws IOException {
        while (true){
            String msg = br.readLine();
            out.writeUTF(msg);
            out.flush();
            if (msg.equalsIgnoreCase("/q")) break;
        }

        br.close();
        out.close();
    }
}
