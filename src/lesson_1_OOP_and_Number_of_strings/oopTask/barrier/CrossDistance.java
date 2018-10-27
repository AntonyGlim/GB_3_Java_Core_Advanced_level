package lesson_1_OOP_and_Number_of_strings.oopTask.barrier;

import lesson_1_OOP_and_Number_of_strings.oopTask.team.Player;

//Кросс
public class CrossDistance implements Barier  {
    int length;

    public CrossDistance(int length){
        this.length = length;
    }

    @Override
    public void doit(Player player) {
        player.run(length);
    }

    @Override
    public void barierInfo() {
        System.out.println("Длина дистанции: " + length + " м.");
    }
}
