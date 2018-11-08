package lesson_6_Work_with_network.consoleСhatTask.Temp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SendMessage implements Runnable {

    private DataOutputStream out;
    private BufferedReader br;
    private Socket socket;

    public SendMessage(BufferedReader br, DataOutputStream out, Socket socket){
        this.out = out;
        this.br = br;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!socket.isOutputShutdown()) {
                String clientMsg = br.readLine();
                out.writeUTF(clientMsg);
                out.flush();
                if(clientMsg.equalsIgnoreCase("/q")) break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
