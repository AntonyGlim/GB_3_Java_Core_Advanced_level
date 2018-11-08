package chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketThread implements Runnable {

    private Socket s = null;
    private Scanner in = null;
    private PrintWriter out = null;
    private boolean exit = true;
    private String inMessage = null;
    private String outMessage = null;
    private ArrayList<Socket> listSocket = null;

    public SocketThread(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            System.out.println("User connect...");
            ListSocket.addSocketToList(s);
            in = new Scanner(s.getInputStream());
            while (exit) {
                inMessage = in.nextLine();
                listSocket = ListSocket.getListSocket();
                for (Socket socket : listSocket) {
                    if (!socket.equals(s)) {
                        out = new PrintWriter(socket.getOutputStream());
                        out.println(inMessage);
                        out.flush();
                    }
                }
                if (inMessage.trim().equals("exit")) {
                    exit = false;
                }
            }
            ListSocket.removeSocketWithList(s);
            System.out.println("User disconnect...");
        } catch (IOException ex) {
            try {
                s.close();
            } catch (IOException ex1) {
                Logger.getLogger(SocketThread.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(SocketThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
