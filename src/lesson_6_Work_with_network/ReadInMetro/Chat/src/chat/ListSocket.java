package chat;

import java.net.Socket;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Администратор
 */
public class ListSocket {

    private static ArrayList<Socket> listSocket = new ArrayList<Socket>();

    public synchronized static ArrayList<Socket> getListSocket() {
        return ListSocket.listSocket;
    }

    public synchronized static void addSocketToList(Socket s) {
        ListSocket.listSocket.add(s);
    }

    public synchronized static void removeSocketWithList(Socket s) {
        ListSocket.listSocket.remove(s);
    }
}
