package lesson_6_Work_with_network.consoleСhatTask.server;


import java.io.IOException;

/**
 * В этом классе мы просто создаем нового сервера
 */
public class ServerStart {
    public static void main(String[] args) {
        try {

            new ServerMain();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
