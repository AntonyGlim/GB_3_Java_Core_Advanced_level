package lesson_6_Work_with_network.methodicalInstructionsEchoServer.MainServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket client = null;
        try {
            serverSocket = new ServerSocket(8716);
            System.out.println("Сервер запущен, ожидаем подключения...");
            client = serverSocket.accept();
            System.out.println("Клиент подключился");
            Scanner sc = new Scanner(client.getInputStream());
            PrintWriter pw = new PrintWriter(client.getOutputStream());
            while (true) {
                String msg = sc.nextLine();
                if (msg.equals("/q")) {
                    pw.println("Вы решили прервать соединение...");
                    break;
                }
                pw.println("Эхо: " + msg);
                pw.flush();
            }
        } catch (IOException e) {
            System.out.println("Ошибка инициализации сервера");
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
