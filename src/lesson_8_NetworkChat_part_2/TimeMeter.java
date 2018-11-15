package lesson_8_NetworkChat_part_2;

/**
 * Класс вычисляет время выполнения методов (операций, вычислений)
 */
public class TimeMeter {

    private String title;                       //Описание выполняемого метода (операции, вычисления)
    private long workingTime;                   //Время, сколько длилось выполнение
    private long startTime;                     //Время начала отсчета
    private long stopTime;                      //Время конца отсчета
    private boolean isTimePassed = false;


    public TimeMeter () {
        Thread t1 = new Thread (new Runnable() {
            @Override
            public void run() {
                System.out.println("1");
            }
        });
        for (int i = 0; i < 12; i++) {
            t1.start();
            try {
                t1.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t1.notify();
        }
    }


    public boolean isTimePassed() {
        return isTimePassed;
    }

    /**
     * Запишет время начала
     */
    public void timeStart(){
        startTime = System.currentTimeMillis();
    }

    /**
     * Запишет время завершения
     */
    public void timeStop(){
        stopTime = System.currentTimeMillis();
    }

    /**
     * Вернет разницу между отсетками старт и стоп
     * @param title - название
     * @return - сообщение
     */
    public String timeInfo(String title){
        this.title = title;
        workingTime = stopTime - startTime;
        String timeMessage = (title + ". Длительность выполнения: " + workingTime + " мс.");
        return timeMessage;
    }

    /**
     * Вернет время работы
     * @return
     */
    public long getWorkingTime() {
        return workingTime;
    }


}
