package lesson_1_OOP_and_Number_of_strings.oopTask.team;

public class Human extends Player  {
    public Human(String name) {
        super("Человек", name);
        maxRunDistance = rnd(400, 600);
        maxSwimDistance = rnd(100, 300);
        maxJumpHeight = rnd(90, 120);
    }
}
