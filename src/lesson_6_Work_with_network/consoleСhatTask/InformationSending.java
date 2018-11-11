package lesson_6_Work_with_network.consoleСhatTask;

import java.awt.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

/**
 * Класс предназначен для отправки информации вовне
 */
public class InformationSending implements Runnable {

    private Socket socket;
    private DataOutputStream out;                                           //Нить для вывода информации
    private Scanner sc;                                                     //Используем Scanner для чтения из консоли

    public InformationSending(Socket socket) throws IOException {
        this.socket = socket;
        out = new DataOutputStream(socket.getOutputStream());
        sc = new Scanner (new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        try {
            while (true) {                                                   //Отправка сообщений
                    String msg = sc.nextLine();
                    out.writeUTF(msg);
                    out.flush();
                    if (msg.equalsIgnoreCase("/q"))
                        break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        sc.close();
        System.out.println("sc.close() - done");

        try {
            out.close();
            System.out.println("out.close() - done");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
            System.out.println("socket.close() - done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}