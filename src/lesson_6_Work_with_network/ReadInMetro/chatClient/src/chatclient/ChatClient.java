package chatclient;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatClient {

    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "cp866"));
            System.out.println("Client starting...");
            Socket s = new Socket("192.168.0.17",9222);
            System.out.println("Connect to server...");
            Thread threadIn = new Thread(new SocketInputThread(s));
            Thread threadOut = new Thread(new SocketOutputThread(s));
            threadIn.start();
            threadOut.start();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
