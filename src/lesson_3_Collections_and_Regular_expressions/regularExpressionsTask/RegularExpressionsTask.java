/**
 *  Необходимо из консоли считать пароль и проверить валидность,
 *  результат будет true или false
 *
 *  Требования:
 *  1. Пароль должен быть не менее 8ми символов.
 *  2. В пароле должно быть число
 *  3. В пароле должна быть хотя бы 1 строчная буква
 *  4. В пароле должна быть хотя бы 1 заглавная буква
 */

package lesson_3_Collections_and_Regular_expressions.regularExpressionsTask;

import java.util.Scanner;

public class RegularExpressionsTask {

    public static void main(String[] args) {
        System.out.println("Введите ваш вариант пароля:");
        boolean isValid = userEntersPasword();
    }

    /**
     * Метод предназначен для считывания введенных пользователем значений до тех пор,
     * пока он не получит валидное значение
     * @return целочисленный порядковый номер дня введенный пользователем
     */
    public static boolean userEntersPasword() {
        String dayWhichUserIn;                                                              //Строка, которую ввел пользователь
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
}
