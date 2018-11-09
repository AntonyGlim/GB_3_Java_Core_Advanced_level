package lesson_6_Work_with_network.consoleСhatTask.server;

import lesson_6_Work_with_network.consoleСhatTask.InformationGetting;
import lesson_6_Work_with_network.consoleСhatTask.InformationSending;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static lesson_6_Work_with_network.consoleСhatTask.Constants.PORT;

/**
 * Класс описывает логику работы Сервера
 */
public class ServerMain {

    private ServerSocket serverSocket;                                                  //Серверный Сокет для подключения Клиентов
    private Socket client;                                                              //Сокет предназначен для связи Сервера с Клиентами

    ServerMain() throws IOException, InterruptedException {

        serverSocket = new ServerSocket(PORT);                                          //Создаем сетевой Сокет
        System.out.println("Сервер ожидает подключения пользователя...");
        client = serverSocket.accept();                                                 //Ждем подключения Клиента
        System.out.println("Пользователь подключился.");

        Thread threadOut = new Thread(new InformationSending(client));                  //Создаем поток отвечающий за отправку информации
        Thread threadIn = new Thread(new InformationGetting(client));                   //Создаем поток отвечающий за получение информации

        threadIn.setDaemon(true);                                                       //Поток получения переводим в фоновый режим (делать этого не обязательно)
        threadIn.start();

        threadOut.start();
        threadOut.join();                                                               //Ждем выполнения основного потока

        System.out.println("Соединение прервано.");
        client.close();                                                                 //Закрываем Сокет
        serverSocket.close();                                                           //Закрываем Сетевой Сокет

    }
}
