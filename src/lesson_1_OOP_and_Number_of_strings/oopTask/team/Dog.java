package lesson_1_OOP_and_Number_of_strings.oopTask.team;

public class Dog extends Player  {

    public Dog(String name) {
        super("Пес", name);
        maxRunDistance = rnd(300, 700);
        maxSwimDistance = rnd(200, 400);
        maxJumpHeight = rnd(80, 150);
    }
}
