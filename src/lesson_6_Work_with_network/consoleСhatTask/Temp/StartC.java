package lesson_6_Work_with_network.consoleСhatTask.Temp;

import java.io.IOException;

public class StartC {
    public static void main(String[] args) {
        try {
            new Client();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
