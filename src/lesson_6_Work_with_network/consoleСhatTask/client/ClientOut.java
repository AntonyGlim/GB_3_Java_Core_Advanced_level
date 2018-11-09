package lesson_6_Work_with_network.consoleСhatTask.client;

import lesson_6_Work_with_network.consoleСhatTask.MessageOut;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientOut implements Runnable{

    private Socket socket;
    private DataOutputStream out;
    private BufferedReader br;

    public ClientOut(Socket socket) throws IOException {
        this.socket = socket;
        out = new DataOutputStream(socket.getOutputStream());
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        try {
            new MessageOut(br, out);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
