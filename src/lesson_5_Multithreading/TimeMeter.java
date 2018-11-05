package lesson_5_Multithreading;

/**
 * Класс вычисляет время выполнения методов (операции, вычисления)
 */
public class TimeMeter {

    private String title;                       //Описание выполняемого метода (операции, вычисления)

    private long workingTime;                   //Время, сколько длилось выполнение
    private long startTime;                     //Время начала отсчета
    private long stopTime;                      //Время конца отсчета

    TimeMeter(String title){
        this.title = title;
    }

    public void timeStart(){
        startTime = System.currentTimeMillis();
    }

    public void timeStop(){
        stopTime = System.currentTimeMillis();
    }

    public void timeInfo(){
        workingTime = stopTime - startTime;
        System.out.println(this.title + ". Длительность выполнялся: " + workingTime + " мс.");
    }

    public String timeInfo(String title){
        String timeMessage;
        workingTime = stopTime - startTime;
        timeMessage = (title + ". Длительность выполнялся: " + workingTime + " мс.");
        return timeMessage;
    }
}
