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
 * Отличие первого метода от второго:
 * Первый просто бежит по массиву и вычисляет значения.
 * Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и потом склеивает эти массивы обратно в один.
 *
 * Пример деления одного массива на два:
 * System.arraycopy(arr, 0, a1, 0, h);
 * System.arraycopy(arr, h, a2, 0, h);
 *
 * Пример обратной склейки:
 * System.arraycopy(a1, 0, arr, 0, h);
 * System.arraycopy(a2, 0, arr, h, h);
 *
 * Примечание:
 * System.arraycopy() копирует данные из одного массива в другой:
 * System.arraycopy(массив-источник, откуда начинаем брать данные из массива-источника, массив-назначение, откуда начинаем записывать данные в массив-назначение, сколько ячеек копируем)
 * По замерам времени:
 * Для первого метода надо считать время только на цикл расчета:
 * for (int i = 0; i < size; i++) {
 * arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
 * }
 * Для второго метода замеряете время разбивки массива на 2, просчета каждого из двух массивов и склейки.
 */



package lesson_5_Multithreading;

public class MainMultithreadingTask {

    static final int size = 101;                    //Размеры массива
    static float[] arrS = new float[size];          //Массив для 1 потока
    static float[] arrM = new float[size];          //Массив для многопоточности
    static float[][] arrMulti = new float[size][];          //Массив для многопоточности
    static short threadCount = 5;                   //Количество потоков
    static String[][] timeInfo = new String[4][2];  //Массив для контроля времени

    public static void main(String[] args) {

    //Наполняем массив значениями (1 поток)
        long timeStart = System.currentTimeMillis();
        arrS = fillArray(arrS);
        timeInfo[0][0] = "Время заполнения (1 поток):";
        timeInfo[0][1] = Long.toString((System.currentTimeMillis() - timeStart)) + " мc.";
        printArray(arrS);

    //Вычисляем значения по формуле (1 поток)
        timeStart = System.currentTimeMillis();
        arrS = calculatingValuesInArray(arrS);
        timeInfo[1][0] = "Время вычисления значений по формуле (1 поток):";
        timeInfo[1][1] = Long.toString((System.currentTimeMillis() - timeStart)) + " мc.";
        printArray(arrS);

    //Наполняем массив значениями (1 поток)
        timeStart = System.currentTimeMillis();
        arrM = fillArray(arrS);
        timeInfo[2][0] = "Время заполнения (1 поток):";
        timeInfo[2][1] = Long.toString((System.currentTimeMillis() - timeStart)) + " мc.";
        printArray(arrM);
        arrMulti = segmentationArray(arrM, threadCount);
        printDoubleArray(arrMulti);
        float[] f = gluingArray(arrMulti, size);
        printArray(f);




        printDoubleArray(timeInfo);
    }

    /**
     * Метод выведет массив в консоль
     * @param arr - получает массив
     */
    public static void printArray(float[] arr){
        for (float f : arr)
            System.out.println(f);
    }

    /**
     * Метод выведет 2 мерный массив в консоль
     * @param arr - получает массив
     */
    public static void printDoubleArray(String[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void printDoubleArray(float[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Метод который заполнит массив значениями (1 поток)
     * @param arr - на вход подается пусой массив
     * @return - возвращается массив со значениями
     */
    public static float[] fillArray(float[] arr) {
        int count = 1;
        for (int i = 0; i < arr.length ; i++) {
            arr[i] = count;
        }
        return arr;
    }

    /**
     * Расчет элементов массива по формуле (1 поток)
     * @param arr - на вход подается массив
     * @return - возвращается массив со значениями
     */
    public static float[] calculatingValuesInArray(float[] arr) {
        for (int i = 0; i < arr.length ; i++) {
            arr[i] = (float)((arr[i] + 14) * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        return arr;
    }

    /**
     * Метод делит массив на несколько подмассивов
     * @param arr - массив, который будет разделен на части
     * @param count - на сколько частей будет разделен массив
     * @return - Двумерный массив, состоящий из подмассивов
     */
    public static float[][] segmentationArray(float[] arr, short count){
        int startIndex = 0;
        int numberOfElementsInArray = arr.length / count;
        int residueOfElements = arr.length % count;
        float[][] array = new float[count][];

        for (int i = 0; i < count - 1; i++) {
            array[i] = new float[numberOfElementsInArray];
            System.arraycopy(arr, startIndex, array[i], 0, numberOfElementsInArray);
            startIndex += numberOfElementsInArray;
        }
        array[count-1] = new float[numberOfElementsInArray + residueOfElements];
        System.arraycopy(arr, startIndex, array[count-1], 0, array[count-1].length);

        return array;
    }

    public static float[] gluingArray(float[][] arr, int size){
        float[] array = new float[size];
        int startIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            System.arraycopy(arr, startIndex, array[i], 0, arr[i].length);
            startIndex += arr[i].length - 1;
        }
        return array;
    }

}

class FillArrayMulti implements Runnable {

    @Override
    public void run() {

    }
}

class CalculatingValuesInArray implements Runnable{

    @Override
    public void run() {

    }
}
