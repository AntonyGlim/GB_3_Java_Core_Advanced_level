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

    static final int size = 100;
    static float[] arrS = new float[size];;
    static float[] arrM = new float[size];;
    static byte threadCount = 1;

    public static void main(String[] args) {

        MultithreadingTask st = new MultithreadingTask();

        long timeStart = System.currentTimeMillis();
        arrS = st.fillArray(arrS);                                              //Наполняем массив значениями
        long timeMethodWorking = (System.currentTimeMillis() - timeStart);
        printArray(arrS);
        System.out.println(timeMethodWorking + " мc.");


        timeStart = System.currentTimeMillis();
        arrS = st.calculatingValuesInArray(arrS);                               //Расчитываем каждое значение массива по формуле
        timeMethodWorking = (System.currentTimeMillis() - timeStart);
        printArray(arrS);
        System.out.println(timeMethodWorking + " мc.");

//        MultithreadingTask mt = new MultithreadingTask();
//
//        arrM = mt.fillArray(arrM, threadCount);
//
//        long timeStart = System.currentTimeMillis();
//        arrM = mt.calculatingValuesInArray(arrM, threadCount);
//        long timeMethodWorking = (System.currentTimeMillis() - timeStart);
//        System.out.println(timeMethodWorking + " мc.");

    }
    /**
     * Метод выведет массив в консоль
     * @param arr
     */
    public static void printArray(float[] arr){
        for (float f : arr)
            System.out.println(f);
    }
}

class MultithreadingTask {


    /**
     * Метод который заполнит массив значениями (1 поток)
     * @param arr - на вход подается пусой массив
     * @return - возвращается массив со значениями
     */
    public float[] fillArray(float[] arr) {
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
    public float[] calculatingValuesInArray(float[] arr) {
        for (int i = 0; i < arr.length ; i++) {
            arr[i] = (float)((arr[i] + 14) * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        return arr;
    }

    public float[] fillArray(float[] arr, byte threadCount) {
        String methodsTitle = "Заполнение массива (Количество потоков = " + threadCount + "): ";
        System.out.println(methodsTitle);
        float[][] multidimensionalArray = segmentationArray(arr, threadCount);

        for (int i = 0; i < multidimensionalArray.length; i++) {
            float[] tempArray = multidimensionalArray[i];
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < tempArray.length; j++) {
                        tempArray[j] = 1;
                    }
                }
            }).start();
        }
        return arr;
    }



    public float[] calculatingValuesInArray(float[] arr, byte threadCount) {
        String methodsTitle = "Расчет элементов массива по формуле (Количество потоков = " + threadCount + "): ";
        System.out.println(methodsTitle);
        float[][] multidimensionalArray = segmentationArray(arr, threadCount);

        for (int i = 0; i < multidimensionalArray.length; i++) {
            float[] tempArray = multidimensionalArray[i];
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < tempArray.length; j++) {
                        tempArray[j] = (float)((arr[j] + 14) * Math.sin(0.2f + j / 5) * Math.cos(0.2f + j / 5) * Math.cos(0.4f + j / 2));

                    }
                }
            }).start();
            System.out.println("Поток: " + i);
        }

        return arr;
    }

    public static float[][] segmentationArray(float[] arr, int count){

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

        for (int i = 0; i < array.length ; i++) {
            for (int j = 0; j < array[i].length; j++) {
//                System.out.print(array[i][j] + " ");
            }
//            System.out.println();
        }
        return array;
    }
}
