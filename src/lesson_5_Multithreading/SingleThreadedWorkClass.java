/**
 * 1. Необходимо написать два метода, которые делают следующее:
 * 1) Создают одномерный длинный массив, например:
 * static final int size = 10000000;
 * static final int h = size / 2;
 * float[] arr = new float[size];
 * 2) Заполняют этот массив единицами;
 * 3) Засекают время выполнения: long a = System.currentTimeMillis();
 * 4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
 * arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
 * 5) Проверяется время окончания метода System.currentTimeMillis();
 * 6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a);
*/

package lesson_5_Multithreading;

public class SingleThreadedWorkClass implements LessonFiveTask {
    public String numberOfThread = "1 поток: ";
    public long timeMethodWorking = 0;


    @Override
    public float[] createArray(int size) {
        float[] arr = new float[size];
        System.out.println(numberOfThread + "массив создан");
        return arr;
    }

    @Override
    public float[] fillArray(float[] arr) {
        for (float f : arr){
            f = 1;
            System.out.println(numberOfThread + f);
        }
        return arr;
    }

    @Override
    public float[] calculatingValuesInArray(float[] arr) {
        long timeStart = System.currentTimeMillis();
        for (int i = 0; i < arr.length ; i++) {
            arr[i] = (float)((arr[i] + 14) * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            System.out.println(numberOfThread + arr[i]);
        }
        timeMethodWorking = (System.currentTimeMillis() - timeStart);
        System.out.println("Метод выполнялся " + timeMethodWorking + " мc.");
        return arr;
    }
}
