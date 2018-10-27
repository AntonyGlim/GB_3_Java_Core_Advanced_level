/**
 * Рабочая неделя состоит из 5 рабочих и 2 выходных дней.
 * В каждом рабочем дне по 8 рабочих часов.
 * В выходные дни рабочих часов, соответственно, нет.
 * Напишите программу, которая бы на вход принимала день недели, а возвращала бы
 * количество рабочих часов до конца недели.
 * Пример: ПН - 40 часов; ПТ - 8 часов; ВС - день выходной!
 * Программу необходимо реализовать через Enum используя конструкторы
 */

package lesson_2_Exceptions_and_Enum.enumTask;

import java.util.Scanner;

public class Lesson2Enum {

    public static void main(String[] args) {

        System.out.println("Введите день недели, информацию о котором Вы бы хотели узнать.");
        System.out.println("Это должен быть его порядковый номер.");

        int dayWhichUserIn = userEntersData();                                                  //Пользователь вводит порядковый номер дня недели

        for (WorkWeek d : WorkWeek.values()) {
            while (d.ordinal() == (dayWhichUserIn - 1)) {                                       //if у меня выдавал все значения enum. Поэтому while.
                System.out.println("Ваш день: " + d.getRuName());                               //Выводим название дня
                System.out.println("До конца недели осталось: " + d.getHoursBeforeEndWeek());   //Выводим информацию об оставшихся рабочих часах
                break;
            }
        }
    }


    /**
     * Метод предназначен для считывания введенных пользователем значений до тех пор,
     * пока он не получит валидное значение
     * @return целочисленный порядковый номер дня введенный пользователем
     */
    public static int userEntersData() {
        String dayWhichUserIn;                                                                  //Строка, которую ввел пользователь
        Scanner in = new Scanner(System.in);

            do {                                                                                //Защита от не корректных данных
                dayWhichUserIn = in.nextLine();                                                 //Принимаем значение пользователя
            } while (!isValid(dayWhichUserIn));                                                 //До тех пор, пока не получим валидные значения

        int result = Integer.parseInt(dayWhichUserIn);                                          //Преобразовываем введенное пользователем в число
        return result;
    }


    /**
     * Метод проверяет полученый аргумент на соответствие требованиям
     * @param str - строка с текстом
     * @return соответствие строки требованиям
     */
    private static boolean isValid(String str){
        boolean isTrue = true;                                                                      //Изначально соответствует требованиям
        int dayWhichUserIn;
        if (!str.matches("-?\\d+")){                                                            //Если не целое число (отрицательные тоже распознаются)
            System.err.println("Значение должно быть числом!");
            isTrue = false;
        } else {
            dayWhichUserIn = Integer.parseInt(str);                                                 //Преобразовываем введенное пользователем в число
            if (dayWhichUserIn < 1 || dayWhichUserIn > 7) {                                         //Если число не попадает в указанный диапазон
                System.err.println("Такого дня недели нет! Попробуйте еще раз!");
                isTrue = false;
            }
        }
        return isTrue;
    }
}


/**
 * В перечислении собраны дни недели с информацией о количестве рабочих часов,
 * оставшихся до конца недели
 */
enum WorkWeek{
    MONDAY("Понедельник", "40 часов"),
    TUESDAY("Вторник", "32 часа"),
    WEDNESDAY("Среда", "24 часа"),
    THURSDAY("Четверг", "16 часов"),
    FRIDAY("Пятница", "8 часов"),
    SATURDAY("Суббота", "День выходной"),
    SANDAY("Воскресенье", "День выходной");

    private String ruName;                          //Название дня недели
    private String hoursBeforeEndWeek;              //Информация о количестве рабочих часов, оставшихся до конца недели

    public String getRuName() {
        return ruName;
    }

    public String getHoursBeforeEndWeek() {
        return hoursBeforeEndWeek;
    }

    WorkWeek(String ruName, String hoursBeforeEndWeek) {
        this.ruName = ruName;
        this.hoursBeforeEndWeek = hoursBeforeEndWeek;
    }
}
