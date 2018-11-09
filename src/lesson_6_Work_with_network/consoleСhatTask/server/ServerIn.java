package lesson_6_Work_with_network.consoleСhatTask.server;

import lesson_6_Work_with_network.consoleСhatTask.MessageIn;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

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
            new MessageIn(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
