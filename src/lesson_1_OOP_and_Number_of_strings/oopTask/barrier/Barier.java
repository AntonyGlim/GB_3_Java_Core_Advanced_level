package lesson_1_OOP_and_Number_of_strings.oopTask.barrier;

import lesson_1_OOP_and_Number_of_strings.oopTask.team.Player;

public interface Barier {
    void doit(Player player);       //Участник должен пройти какое-то препядствие
    void barierInfo();              //Информация о барьере для отладки
}
