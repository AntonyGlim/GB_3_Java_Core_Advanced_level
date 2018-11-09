package lesson_6_Work_with_network.consoleСhatTask.client;

import java.io.IOException;
import java.net.Socket;

import static lesson_6_Work_with_network.consoleСhatTask.Constants.IPADRESS;
import static lesson_6_Work_with_network.consoleСhatTask.Constants.PORT;

public class ClientMain {

    private Socket socket;

    ClientMain() throws IOException, InterruptedException {
        socket = new Socket(IPADRESS, PORT);

        Thread threadIn = new Thread(new ClientIn(socket));
        Thread threadOut = new Thread(new ClientOut(socket));

        threadIn.setDaemon(true);
        threadIn.start();

        threadOut.start();
        threadOut.join();

        System.out.println("Соединение прервано (from ClientMain)");
        socket.close();
    }

}
