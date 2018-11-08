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

package lesson_6_Work_with_network.consoleСhatTask.server;

import lesson_6_Work_with_network.consoleСhatTask.client.ClientMain;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static lesson_6_Work_with_network.consoleСhatTask.Constants.PORT;

public class ServerMain {

    DataInputStream in;
    DataOutputStream out;
    ServerSocket serverSocket;
    Socket client;


    public ServerMain() {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Сервер ожидает подключения пользователя...");
            client = serverSocket.accept();


            out = new DataOutputStream(client.getOutputStream());
            in = new DataInputStream(client.getInputStream());
            String userNik;
            userNik = in.readUTF();
            out.writeUTF("Ваш ник: " + userNik);
            System.out.println("Пользователь с ником: " + userNik + " подключился.");

            while (!client.isClosed()){
                String msg = in.readUTF();
                if (msg.equalsIgnoreCase("/q")){
                    System.out.println(userNik + " решил покинуть чат...");
                    out.writeUTF("Вы решили покинуть чат, соединение будет прервано.");
                    out.flush();
                    break;
                }
                System.out.println(userNik + ": " + msg);
                out.writeUTF(userNik + " Эхо: " + msg);
                out.flush();
            }

            in.close();
            out.close();
            client.close();

            System.out.println("Соединение закрыто.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
