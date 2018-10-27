package lesson_1_OOP_and_Number_of_strings.oopTask.team;

public class Badger extends Player {

    public Badger(String name) {
        super("Барсук", name);
        maxRunDistance = rnd(250, 750);
        maxSwimDistance = rnd(300, 400);
        maxJumpHeight = rnd(100, 160);
    }
}