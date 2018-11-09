package lesson_6_Work_with_network.consoleСhatTask.client;

import java.io.IOException;
import java.net.Socket;

import static lesson_6_Work_with_network.consoleСhatTask.Constants.IPADRESS;
import static lesson_6_Work_with_network.consoleСhatTask.Constants.PORT;

public class ClientMain {

    private Socket socket;

    ClientMain() throws IOException, InterruptedException {
        socket = new Socket(IPADRESS, PORT);

        Thread threadOut = new Thread(new ClientOut(socket));
        Thread threadIn = new Thread(new ClientIn(socket));

        threadIn.start();
        threadOut.start();

        threadIn.join();
        threadOut.join();

        System.out.println("Соединение прервано (from ClientMain)");
        socket.close();
    }

}
