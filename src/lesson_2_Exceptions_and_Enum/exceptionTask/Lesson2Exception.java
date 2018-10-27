/**
 * 1. Напишите метод, на вход которого подается двумерный строковый массив размером 4х4,
 * при подаче массива другого размера необходимо бросить исключение MyArraySizeException.
 *
 * 2. Далее метод должен пройтись по всем элементам массива, преобразовать в int,
 * и просуммировать. Если в каком-то элементе массива преобразование не удалось
 * (например, в ячейке лежит символ или текст вместо числа), должно быть брошено
 * исключение MyArrayDataException – с детализацией, в какой именно ячейке лежат неверные данные.
 *
 * 3. В методе main() вызвать полученный метод, обработать возможные исключения MySizeArrayException
 * и MyArrayDataException и вывести результат расчета.
 */

package lesson_2_Exceptions_and_Enum.exceptionTask;

public class Lesson2Exception {


    public static void main(String[] args) {

        int sum = 0;                            //Для подсчета суммы
        boolean wasException = false;           //Были в программе исключения?
        final int SIZE = 4;                     //Размеры массива

        String[][] arrOfString = {              //Создаем массив с заведомо не правильными параметрами
                {"64", "-45", "__", "1368"},
                {"4785", "-9", "5", "-890"},
                {"89", "1325", "066", "4"},
                {"142", "852", "3", "-123", "4"},
        };

        try {                                           //Ловим исключения
            sum = sumOfArray(arrOfString, SIZE);        //Метод sumOfArray вернет сумму элементов массива, если данные валидны
        } catch (MyArrayDataException e) {              //Исключение наследуется от боее низкого класса поэтому идет 1-м
            e.printStackTrace();
            wasException = true;                        //Говорим, что исключение произошло
        } catch (MySizeArrayException e){               //Исключение наследуется от RuntimeException поэтому запис. последним
            e.printStackTrace();
            wasException = true;
        }

        if(!wasException) {                     //Если ошибки небыло
            System.out.println("Сумма элементов массива: " + sum);
        }
    }


    /**
     * Метод вычисляет сумму элементов массива
     * @param arr - двумерный массив строк
     * @param size - размеры массива
     * @return - сумму всех элементов;
     * @throws MyArrayDataException - если строку нельзя преобразовать в число
     * @throws MySizeArrayException - если размеры массива не соответствуют size
     */
    public static int sumOfArray(String[][] arr, int size) throws MyArrayDataException, MySizeArrayException{
        int sum = 0;
        if (arr.length != size) {                                  //Контролируем размеры массива по кол. строк
            throw new MySizeArrayException(size);
        }
        for (int i = 0; i < arr.length; i++){
            if (arr[i].length != size) {                           //Контролируем размеры массива по кол. элементов в строках
                throw new MySizeArrayException(size);
            }
            for (int j = 0; j < arr[i].length; j++){
                if (arr[i][j].matches("-?\\d+")){           //Используем регулярные выражения asis
                    sum += Integer.parseInt(arr[i][j]);
                } else {
                    throw new MyArrayDataException("Элемент с индексом " + i + " " + j +
                            " имеет не корректное значение");
                }
            }
        }
        return sum;                                                 //Возвращаем результат
    }

}


/**
 * Класс предназначен для отлова NumberFormatException исключений
 */
class MyArrayDataException extends NumberFormatException{
    public MyArrayDataException(String message){
        super(message);
    }
}


/**
 * Класс предназначен для контроля заданной размерности массива
 */
class MySizeArrayException extends RuntimeException{
    public MySizeArrayException(int size){
        super("Массив имеет недопустимый размер! Он должен быть строго " + size + "х" + size);
    }
}