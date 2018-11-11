package lesson_6_Work_with_network.simpleEchoServerAndClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SimpleClient {

    static Socket socket = null;

    public static void main(String[] args) {

        try {
            socket = new Socket("localhost", 8856);

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            Thread tRead = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        try {
                            String msg = in.readUTF();
                            if (msg.equalsIgnoreCase("/q")){
                                out.writeUTF(msg);
                                break;
                            }
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
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
