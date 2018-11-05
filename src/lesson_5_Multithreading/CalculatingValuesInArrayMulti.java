package lesson_5_Multithreading;

/**
 * Метод описывает логику работы для потока
 */
public class CalculatingValuesInArrayMulti implements Runnable {

    private float[] arr;

    CalculatingValuesInArrayMulti(float[] arr){
        this.arr = arr;
    }

    @Override
    public void run() {
        MainMultithreadingTask.calculatingValuesInArray(arr);
    }


    public float[] getArr() {
        return arr;
    }
}
