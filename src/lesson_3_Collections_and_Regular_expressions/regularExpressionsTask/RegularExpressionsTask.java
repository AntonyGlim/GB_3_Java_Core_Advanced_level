/**
 *  Необходимо из консоли считать пароль и проверить валидность,
 *  результат будет true или false
 *  Требования:
 *  1. Пароль должен быть не менее 8ми символов.
 *  2. В пароле должно быть число
 *  3. В пароле должна быть хотя бы 1 строчная буква
 *  4. В пароле должна быть хотя бы 1 заглавная буква
 */

package lesson_3_Collections_and_Regular_expressions.regularExpressionsTask;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressionsTask {

    public static void main(String[] args) {
        System.out.println("Введите ваш вариант пароля:");
        Scanner in = new Scanner(System.in);
        System.out.println(isValid(in.nextLine()));
    }


    /**
     * Метод проверяет полученый аргумент на соответствие требованиям
     * @param str - строка с текстом
     * @return соответствие строки требованиям
     */
    private static boolean isValid(String str){
        Pattern p = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8}+$");
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
