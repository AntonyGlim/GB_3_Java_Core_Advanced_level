package lesson_6_Work_with_network.consoleСhatTask.client;

import lesson_6_Work_with_network.consoleСhatTask.MessageIn;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientIn implements Runnable{

    private Socket socket;
    private DataInputStream in;


    public ClientIn(Socket socket) throws IOException {
        this.socket = socket;
        in = new DataInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        new MessageIn(socket, in);
    }
}
