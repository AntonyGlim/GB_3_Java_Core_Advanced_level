package lesson_6_Work_with_network.consoleСhatTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Класс предназначен для отправки информации вовне
 */
public class InformationSending implements Runnable {

    private Socket socket;
    private DataOutputStream out;                                           //Нить для вывода информации
    private BufferedReader br;                                              //Используем BufferedReader для чтения из консоли

    public InformationSending(Socket socket) throws IOException {
        this.socket = socket;
        out = new DataOutputStream(socket.getOutputStream());
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        try {
            while (true) {                                                   //Отправка сообщений
                    String msg = br.readLine();
                    out.writeUTF(msg);
                    out.flush();
                    if (msg.equalsIgnoreCase("/q"))
                        break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            br.close();
            System.out.println("br.close() - done");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
            System.out.println("out.close() - done");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            socket.close();
//            System.out.println("socket.close() - done");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}