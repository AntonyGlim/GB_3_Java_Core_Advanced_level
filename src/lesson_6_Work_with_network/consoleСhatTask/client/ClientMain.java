/**
 * 1. Написать консольный вариант клиент\серверного приложения, в котором пользователь может писать сообщения,
 * как на клиентской стороне, так и на серверной. Т.е. если на клиентской стороне написать «Привет»,
 * нажать Enter, то сообщение должно передаться на сервер и там отпечататься в консоли. Если сделать то же самое
 * на серверной стороне, то сообщение передается клиенту и печатается у него в консоли. Есть одна особенность,
 * которую нужно учитывать: клиент или сервер может написать несколько сообщений подряд.
 * Такую ситуацию необходимо корректно обработать.
 *
 * Разобраться с кодом с занятия: он является фундаментом проекта-чата
 *
 * *ВАЖНО! * Сервер общается только с одним клиентом, т.е. не нужно запускать цикл,
 * который будет ожидать второго/третьего/n-го клиентов.
 */

package lesson_6_Work_with_network.consoleСhatTask.client;

import lesson_6_Work_with_network.consoleСhatTask.Constants;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientMain {

    private String  userNik;
    private Socket socket;
    private BufferedReader br;
    private DataInputStream in;
    private DataOutputStream out;

    public ClientMain(String userNik){

        this.userNik = userNik;
        try {
            socket = new Socket(Constants.IPADRESS, Constants.PORT);
            br = new BufferedReader(new InputStreamReader(System.in)) ;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            while (!socket.isOutputShutdown()){
                if(br.ready()){
                    String clientMsg = br.readLine();
                    out.writeUTF(clientMsg);
                    out.flush();
                    if(clientMsg.equalsIgnoreCase("/q")) break;
                }
            }
            System.out.println("Соединение будет прервано...");
            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
