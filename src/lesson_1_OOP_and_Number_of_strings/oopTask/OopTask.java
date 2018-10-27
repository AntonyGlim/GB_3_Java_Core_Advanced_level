package lesson_1_OOP_and_Number_of_strings.oopTask;

import lesson_1_OOP_and_Number_of_strings.oopTask.barrier.Course;
import lesson_1_OOP_and_Number_of_strings.oopTask.team.Team;

//Возможности участников генерируются случайно, поэтому запускаем несколько раз, результаты будут разными
//Если задать не верные значения в конструктор Course, программа без предупреждения поставит туда свои значения
public class OopTask {
    public static void main(String[] args) {
        Course course = new Course(300, 150, 100); //Создаем полосу препятствий
        Team team = new Team("Неуловимые","Рыжик", "Шарик", "Проныра", "Саня"); //Создаем команду
        course.doIt(team); //Просим команду пройти полосу препятствий
        team.teamWinnersInfo();  // это и есть showResults()
    }
}

