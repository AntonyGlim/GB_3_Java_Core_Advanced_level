package lesson_5_Multithreading;

public class MultithreadedWorkClass implements LessonFiveTask {
    public String numberOfThreadInfo = "Мульти поток: ";
    public long timeMethodWorking = 0;
    public byte threadCount = 1;        //Количество потоков

    @Override
    public float[] createArray(int size) {
        float[] arr = new float[size];
        System.out.println(numberOfThreadInfo + "массив создан");
        return arr;
    }

    @Override
    public float[] fillArray(float[] arr) {
        return new float[0];
    }

    @Override
    public float[] fillArray(float[] arr, byte threadCount) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (float f : arr){
                    f = 1;
                    System.out.println(numberOfThreadInfo + f);
                }
            }
        });
        return arr;
    }

    @Override
    public float[] calculatingValuesInArray(float[] arr) {
        return new float[0];
    }

    @Override
    public float[] calculatingValuesInArray(float[] arr, byte threadCount) {
        return new float[0];
    }


}
