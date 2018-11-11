package lesson_7_NetworkChat_part_1.methodicalInstruction.MainServer;

import java.util.ArrayList;

interface AuthService {
    void start();
    String getNickByLoginPass(String login, String pass);
    void stop();
}

