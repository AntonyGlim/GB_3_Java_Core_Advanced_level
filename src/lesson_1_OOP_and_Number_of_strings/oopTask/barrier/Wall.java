package lesson_1_OOP_and_Number_of_strings.oopTask.barrier;

import lesson_1_OOP_and_Number_of_strings.oopTask.team.Player;

//Препятствие стена
public class Wall implements Barier {
    int height;

    public Wall(int height){
        this.height = height;
    }

    @Override
    public void doit(Player player) {
        player.jump(height);
    }

    @Override
    public void barierInfo() {
        System.out.println("Высота стены: " + height + " см.");
    }


}
