package lesson_1_OOP_and_Number_of_strings.oopTask.team;

public class Cat extends Player {

    public Cat(String name) {
        super("Кот", name);
        maxRunDistance = rnd(150, 500);
        maxSwimDistance = rnd(100, 300);
        maxJumpHeight = rnd(50, 100);
    }
}
