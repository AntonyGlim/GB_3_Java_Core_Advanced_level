package lesson_5_Multithreading;

public class UserWaitingAnimation implements Runnable {
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(".");
        }
    }
}
