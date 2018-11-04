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
    static final int h = size / 2;
    static float[] arrS;
    static float[] arrM;
    static byte threadCount = 1;


    public static void main(String[] args) {

        LessonFiveTask st = new MultithreadingTask();

//        arrS = st.createArray(size);
//        arrS = st.fillArray(arrS);
//        arrS = st.calculatingValuesInArray(arrS);

        LessonFiveTask mt = new MultithreadingTask();

        arrM = mt.createArray(size);
        arrM = mt.fillArray(arrM, threadCount);

        long timeStart = System.currentTimeMillis();
        arrM = mt.calculatingValuesInArray(arrM, threadCount);
        long timeMethodWorking = (System.currentTimeMillis() - timeStart);
        System.out.println(timeMethodWorking + " мc.");

    }
}

class MultithreadingTask implements LessonFiveTask{

    public long timeMethodWorking = 0;

    @Override
    public float[] createArray(int size) {
        String methodsTitle = "Создание массива: ";
        float[] arr = new float[size];
        System.out.println(methodsTitle + "массив создан");
        return arr;
    }

    @Override
    public float[] fillArray(float[] arr) {
        String methodsTitle = "Заполнение массива в 1 поток: ";
        long timeStart = System.currentTimeMillis();
        for (float f : arr){
            f = 1;
//            System.out.println(methodsTitle + f);
        }
        timeMethodWorking = (System.currentTimeMillis() - timeStart);
        System.out.println(methodsTitle + timeMethodWorking + " мc.");
        return arr;
    }

    @Override
    public float[] fillArray(float[] arr, byte threadCount) {
        String methodsTitle = "Заполнение массива (Количество потоков = " + threadCount + "): ";
        System.out.println(methodsTitle);
        long timeStart = System.currentTimeMillis();
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
        timeMethodWorking = (System.currentTimeMillis() - timeStart);
        System.out.println(methodsTitle + timeMethodWorking + " мc.");
        return arr;
    }

    @Override
    public float[] calculatingValuesInArray(float[] arr) {
        String methodsTitle = "Расчет элементов массива по формуле в 1 поток: ";
        long timeStart = System.currentTimeMillis();
        for (int i = 0; i < arr.length ; i++) {
            arr[i] = (float)((arr[i] + 14) * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
//            System.out.println(methodsTitle + arr[i]);
        }
        timeMethodWorking = (System.currentTimeMillis() - timeStart);
        System.out.println(methodsTitle + timeMethodWorking + " мc.");
        return arr;
    }

    @Override
    public float[] calculatingValuesInArray(float[] arr, byte threadCount) {
        String methodsTitle = "Расчет элементов массива по формуле (Количество потоков = " + threadCount + "): ";
        System.out.println(methodsTitle);
        long timeStart = System.currentTimeMillis();
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
        timeMethodWorking = (System.currentTimeMillis() - timeStart);
        System.out.println(methodsTitle + timeMethodWorking + " мc.");

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
