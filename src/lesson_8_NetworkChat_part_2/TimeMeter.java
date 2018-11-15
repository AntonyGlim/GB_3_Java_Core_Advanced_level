package lesson_8_NetworkChat_part_2;

/**
 * Класс вычисляет время выполнения методов (операций, вычислений)
 */
public class TimeMeter implements Runnable {

    private String title;                       //Описание выполняемого метода (операции, вычисления)
    private long workingTime;                   //Время, сколько длилось выполнение
    private long startTime;                     //Время начала отсчета
    private long stopTime;                      //Время конца отсчета
    boolean isTimePassed = false;

    @Override
    public void run() {
        for (int i = 1; i <= 12 ; i++) {
            try {
                wait(1000);
                System.out.println(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isTimePassed = true;
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
