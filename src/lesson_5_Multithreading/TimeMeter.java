package lesson_5_Multithreading;

/**
 * Класс вычисляет время выполнения методов (операции, вычисления)
 */
public class TimeMeter {

    private String title;                       //Описание выполняемого метода (операции, вычисления)

    private long workingTime;                   //Время, сколько длилось выполнение
    private long startTime;                     //Время начала отсчета
    private long stopTime;                      //Время конца отсчета

    public void timeStart(){
        startTime = System.currentTimeMillis();
    }

    public void timeStop(){
        stopTime = System.currentTimeMillis();
    }

    public String timeInfo(String title){
        this.title = title;
        String timeMessage;
        workingTime = stopTime - startTime;
        timeMessage = (title + ". Длительность выполнения: " + workingTime + " мс.");
        return timeMessage;
    }

    public long getWorkingTime() {
        return workingTime;
    }
}
