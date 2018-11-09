package lesson_6_Work_with_network.consoleСhatTask.client;

import lesson_6_Work_with_network.consoleСhatTask.InformationGetting;
import lesson_6_Work_with_network.consoleСhatTask.InformationSending;

import java.io.IOException;
import java.net.Socket;

import static lesson_6_Work_with_network.consoleСhatTask.Constants.IPADRESS;
import static lesson_6_Work_with_network.consoleСhatTask.Constants.PORT;

/**
 * Класс описывает логику работы Клиента
 */
public class ClientMain {

    private Socket socket;                                                      //Сокет предназначен для связи Клиента с Сервером

    ClientMain() throws IOException, InterruptedException {                     //Пробрасываем исключения в старт

        socket = new Socket(IPADRESS, PORT);                                    //Создаем новый клиентский Сокет

        Thread threadOut = new Thread(new InformationSending(socket));          //Создаем поток отвечающий за отправку Клиентом информации
        Thread threadIn = new Thread(new InformationGetting(socket));           //Создаем поток отвечающий за получение Клиентом информации

        threadIn.setDaemon(true);                                               //Поток получения переводим в фоновый режим
        threadIn.start();

        threadOut.start();
        threadOut.join();                                                       //Ждем выполнения основного потока

        System.out.println("Соединение прервано.");
        socket.close();                                                         //Закрываем Сокет

    }
}
