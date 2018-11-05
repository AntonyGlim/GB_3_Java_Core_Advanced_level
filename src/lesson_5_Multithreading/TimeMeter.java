package lesson_5_Multithreading;

public class TimeMeter {

    private String title;

    private long workingTime;
    private long startTime;
    private long stopTime;

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
        System.out.println(title + " выполнялся " + workingTime + " мс.");
    }
}
