package lesson_5_Multithreading;

public class CalculatingValuesInArrayMulti implements Runnable {

    private float[] arr;

    CalculatingValuesInArrayMulti(float[] arr){
        this.arr = arr;
    }

    @Override
    public void run() {
        for (int i = 0; i < arr.length ; i++) {
            arr[i] = (float)((arr[i] + 14) * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    public float[] getArr() {
        return arr;
    }
}
