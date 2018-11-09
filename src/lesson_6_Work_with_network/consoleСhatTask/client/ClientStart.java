package lesson_6_Work_with_network.consoleСhatTask.client;

import java.io.IOException;

/**
 * В этом классе мы просто создаем нового клиента
 */
public class ClientStart{
    public static void main(String[] args) {
        try {

            new ClientMain();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
