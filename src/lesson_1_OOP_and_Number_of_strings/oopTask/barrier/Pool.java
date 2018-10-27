package lesson_1_OOP_and_Number_of_strings.oopTask.barrier;

import lesson_1_OOP_and_Number_of_strings.oopTask.team.Player;

//Препятствие бассейн с водой
public class Pool implements Barier {
    int length;

    public Pool(int length){
        this.length = length;
    }

    @Override
    public void doit(Player player) {
        player.swim(length);
    }

    @Override
    public void barierInfo() {
        System.out.println("Длина бассейна: " + length + " м.");
    }
}
