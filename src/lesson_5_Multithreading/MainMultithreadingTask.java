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

    static final int size = 10000000;                    //Размеры массива
    static float[] arrS = new float[size];          //Массив для 1 потока
    static float[] arrM = new float[size];          //Массив для многопоточности
    static float[][] arrMulti = new float[size][];          //Массив для многопоточности
    static short threadCount = 2;                   //Количество потоков

    public static void main(String[] args) {

    //Наполняем массив значениями (1 поток)
        TimeMeter timeMeter_1 = new TimeMeter("Заполнение массива единицами в 1 поток");
        timeMeter_1.timeStart();
        arrS = fillArray(arrS);
        timeMeter_1.timeStop();
        System.out.println("1-й массив заполнен:");
        printArray(arrS);

    //Вычисляем значения по формуле (1 поток)
        TimeMeter timeMeter_2 = new TimeMeter("Вычисления элементов массива по формуле в 1 поток");
        timeMeter_2.timeStart();
        arrS = calculatingValuesInArray(arrS);
        timeMeter_2.timeStop();
        System.out.println("1-й массив расчитан:");
        printArray(arrS);

    //Наполняем массив значениями (1 поток)
        arrM = fillArray(arrM);
        System.out.println("2-й массив заполнен:");
        printArray(arrM);

    //Делим массив на части
        TimeMeter timeMeter_4 = new TimeMeter("Деление массива на части (по количеству потоков: " + threadCount + ")");
        timeMeter_4.timeStart();
        arrMulti = segmentationArray(arrM, threadCount);
        System.out.println("2-й массив поделен так:");
        printDoubleArray(arrMulti);
        timeMeter_4.timeStop();

    //Формируем массив с потоками и запускаем массив с потоками
        TimeMeter timeMeter_5 = new TimeMeter("Вычисляем массив в потоках (количество потоков: " + threadCount + ")");
        timeMeter_5.timeStart();
        Thread[] arrThreads = new Thread[threadCount];
        for (int i = 0; i < arrThreads.length; i++) {
            CalculatingValuesInArrayMulti cvi = new CalculatingValuesInArrayMulti(arrMulti[i]);
            arrThreads[i] = new Thread(cvi);
            arrThreads[i].start();
            try {
                arrThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            arrMulti[i] = cvi.getArr();
        }
        timeMeter_5.timeStop();

      //Собираем массив обратно
        TimeMeter timeMeter_6 = new TimeMeter("Собираем массив из частей (по количеству потоков: " + threadCount + ")");
        timeMeter_6.timeStart();
        arrM = gluingArray(arrMulti, size);
        timeMeter_6.timeStop();
        System.out.println("2-й массив после  сборки:");
        printArray(arrM);


        timeMeter_1.timeInfo();
        timeMeter_2.timeInfo();
        timeMeter_4.timeInfo();
        timeMeter_5.timeInfo();
        timeMeter_6.timeInfo();

//        printDoubleArray(timeInfo);
    }

    /**
     * Метод выведет массив в консоль
     * @param arr - получает массив
     */
    public static void printArray(float[] arr){
        for (float f : arr) {
            System.out.print(f + " ");
        }
        System.out.println();
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
            arr[i] = count - 1 + i;
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

    /**
     * Метод собирает массив из несколько подмассивов
     * @param arr - двумерный массив, из которого необходимо собрать один массив
     * @param size - длина массива
     * @return - одномерный массив, собраный из подмассивов
     */
    public static float[] gluingArray(float[][] arr, int size){
        float[] array = new float[size];
        int startIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            System.arraycopy(arr[i], 0, array, startIndex, arr[i].length);
            startIndex += arr[i].length;
        }
        return array;
    }

}

