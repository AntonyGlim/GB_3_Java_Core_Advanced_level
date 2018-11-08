package lesson_6_Work_with_network.consoleСhatTask.Temp;

import java.io.IOException;

public class StartS {
    public static void main(String[] args) {
        try {
            new Server();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
