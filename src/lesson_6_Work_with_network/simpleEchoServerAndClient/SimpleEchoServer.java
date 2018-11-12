package lesson_6_Work_with_network.simpleEchoServerAndClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SimpleEchoServer {
    static ServerSocket serverSocket = null;
    static Socket client = null;

    public static void main(String[] args) {

        try {
            serverSocket = new ServerSocket(8856);
            System.out.println("Ожидаем клиента...");
            client = serverSocket.accept();
            System.out.println("Клиент подключился!");


            DataInputStream in = new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            Thread tRead = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        try {
                            String msg = in.readUTF();
                            if (msg.equalsIgnoreCase("/q")){
                                out.writeUTF("Echo: " + msg);
                                break;
                            }
                            out.writeUTF("Echo: " + msg);
                            System.out.println(msg);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            tRead.start();

            Thread tWrite = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        try {
                            String msg = scanner.nextLine();
                            out.writeUTF(msg);
                            out.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            tWrite.setDaemon(true);
            tWrite.start();


            try {
                tRead.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
