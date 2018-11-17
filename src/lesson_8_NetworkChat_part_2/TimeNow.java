/**
 * Класс осован на классе Date, который доступен в пакете java.util
 * Конструктор класса возвращает дату и время в нужном нам формате
 * Строка имеет вид: 17 Nov 2018 | 11:49:07 |
 *
 */
package lesson_8_NetworkChat_part_2;

import java.util.Date;

public class TimeNow {

    private String str;

    public String TimeNow() {
        Date date = new Date();                         //Инициализация объекта date
        str = date.toString();                          //Дата формата: Sat Nov 17 11:49:07 GMT+03:00 2018
        String[] dateAndTime = str.split(" ");          //Сплитим по пробелам

        StringBuffer result = new StringBuffer();       //Формируем строку того формата, который нужен нам
        result.append(dateAndTime[2] + " ");
        result.append(dateAndTime[1] + " ");
        result.append(dateAndTime[5] + " | ");
        result.append(dateAndTime[3] + " | ");
        str = result.toString();                        //Переводим ее обратно в String. Строка имеет вид: 17 Nov 2018 | 11:49:07 |
        return str;
    }
}
