/**
 * class AuthService предназначен для работы с базой данных.
 * В конкретном случае используется SQLite.
 */

package lesson_8_NetworkChat_part_2.Server;

import java.sql.*;

public class AuthService {

    private static Connection connection;
    private static Statement stmt;

    /**
     * Устанавливаем соединение с БД
     * @throws SQLException
     */
    public static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");                                                   //Регистрация драйвера JDBC в DriverManager (адресуем по URL-адресу)
            connection = DriverManager.getConnection("jdbc:sqlite:mainDB.db");              //DriverManager управляет установлением соединений между БД и соответствующим драйвером
            stmt = connection.createStatement();                                                //Объект Statement используется для выполнения SQL-запросов к БД
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод посылает запрос в БД (по login) и получает обратно ответ в виде ResultSet
     * в котором помещены пароль (его Hash) и nick
     * Метод так же сравнивает введенныи и хранящийся в БД пароль и возвращает nick только в том случае,
     * если Hash паролей совпал.
     * @param login - введенный пользователем логин
     * @param pass - введенный пользователем пароль
     * @return - nick пользователя, если пароли совпали или null
     */
    public static String getNickByLoginAndPass(String login, String pass) {
        String sql = String.format("SELECT nickname, password FROM main\n" +                    //String.format позволяет использовать %s и другие значки
                "WHERE login = '%s'", login);                                                   //Достаем из БД nickname и password по login

        int myHash = pass.hashCode();                                                           //Получаем пароль в виде его hash
        ResultSet rs = null;                                                                    //ResultSet - множетсво результатов, запроса в БД.

        try {
            rs = stmt.executeQuery(sql);                                                        //Метод executeQuery отправляет переданный ему запрос к базе данных и в качестве ответа возвращает результат в виде класса ResultSet.
            if (rs.next()) {                                                                    //Метод ResultSet.next используется для перемещения к следующей строке ResultSet, делая ее текущей.
                String nick = rs.getString(1);                                      //Методы getXXX пытаются сконвертировать низкоуровневые данные в типы данных языка Java
                int dbHash = rs.getInt(2);                                          //Пароли в БД храняться в виде Hash
                if (myHash == dbHash) {                                                         //Если пароли (по Hash) совпали - вернем nick
                    return nick;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Метод добавляет нового пользователя в БД
     * @param login - введенный логин
     * @param pass - введенный пароль
     * @param nick - введенный ник
     */
    public static void addUser(String login, String pass, String nick, String blacklist) {
        String sql = String.format("INSERT INTO main (login, password, nickname, blacklist)" +
                "VALUES ('%s', '%s', '%s', '%s')", login, pass.hashCode(), nick, blacklist);            //Формируем запрос на добавление

        try {
            stmt.execute(sql);                                                                          //execute - метод выполнения SQL-выражений
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Метод добавляет ник пользователя в черный список другого пользователя в БД
     */
    public static void addUserInBlackList(String nickFrom, String nickToBlackList) {

        boolean isNickOnDB = false;
        String blackList = getBlackListStringFromDB(nickFrom);

        if (blackList.equals("unident")){
            blackList = nickToBlackList;
            updateBlackListInDB(nickFrom, blackList);
        } else {
            String[] tokens = blackList.split(" ");
            for (String s : tokens){
                if (s.equals(nickToBlackList)){
                    isNickOnDB = true;
                    break;
                }
            }
            if (!isNickOnDB){
                blackList += " " + nickToBlackList;
                updateBlackListInDB(nickFrom, blackList);
            }
        }
    }

    public static void deleteUserFromBlackList(String nickFrom, String nickToDeleteBlackList) {

        String blackList = getBlackListStringFromDB(nickFrom);

        if (blackList.equals("unident")){
            return;
        } else {
            String[] tokens = blackList.split(" ");
            blackList = "unident";
            for (String s : tokens){
                if (s.equals(nickToDeleteBlackList)){
                    continue;
                } else {
                    blackList += " " + s;
                }
                updateBlackListInDB(nickFrom, blackList);
            }
        }
    }

    /**
     * Метод:
     * Формируем запрос для получения черного списка у клиента из БД по нику
     * Записываем результат в String blackList при помощи ResultSet rs
     * @param nickFrom - ник, черый список которого нужно получить
     * @return - черный список конкретного ника в виде строки
     */
    public static String getBlackListStringFromDB(String nickFrom){
        String sqlGetBlackList = String.format(
                "SELECT blacklist " +
                        "FROM main " +
                        "WHERE nickname = '%s'", nickFrom);

        ResultSet rs = null;                                                                    //ResultSet - множетсво результатов, запроса в БД.
        String blackList = null;
        try {
            rs = stmt.executeQuery(sqlGetBlackList);                                            //Метод executeQuery отправляет переданный ему запрос к базе данных и в качестве ответа возвращает результат в виде класса ResultSet.
            if (rs.next()) {                                                                    //Метод ResultSet.next используется для перемещения к следующей строке ResultSet, делая ее текущей.
                blackList = rs.getString("blacklist");                                          //Методы getXXX пытаются сконвертировать низкоуровневые данные в типы данных языка Java
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blackList;
    }

    /**
     * Метод обновляет в БД черный список ника
     * @param nickFrom - ник, черный список которого необходимо изменить
     * @param blackList - ник, который нужно добавить в черный список
     */
    public static void updateBlackListInDB (String  nickFrom, String blackList){
        String sql = String.format(                                                             //Формируем запрос на добавление
                "UPDATE main " +
                    "SET blacklist = '%s' " +
                    "WHERE nickname = '%s';", blackList, nickFrom
        );
        try {
            stmt.execute(sql);                                                                  //execute - метод выполнения SQL-выражений
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод закрывает соединение с БД
     */
    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
