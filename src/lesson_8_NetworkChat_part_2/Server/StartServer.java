package lesson_8_NetworkChat_part_2.Server;

import java.sql.SQLException;

/**
 * Класс просто запускает наш Сервер
 */
public class StartServer {
    public static void main(String[] args) throws SQLException {
        new Server();
    }
}
