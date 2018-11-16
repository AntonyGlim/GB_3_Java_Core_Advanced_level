package lesson_8_NetworkChat_part_2.Server;

public class WaitingUserAuthorization implements Runnable {

    private int timeWorking;
    private boolean isTimePassed = false;

    public int getTimeWorking() {
        return timeWorking;
    }

    public boolean isTimePassed() {
        return isTimePassed;
    }

    @Override
    public void run() {
        while (true){
            timeWorking = 0;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(isTimePassed);
            }
            timeWorking++ ;
            if(timeWorking >= 3){
                break;
            }
        }
        isTimePassed = true;
    }
}
