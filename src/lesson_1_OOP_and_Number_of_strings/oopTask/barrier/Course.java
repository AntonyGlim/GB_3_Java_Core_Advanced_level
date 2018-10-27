package lesson_1_OOP_and_Number_of_strings.oopTask.barrier;

import lesson_1_OOP_and_Number_of_strings.oopTask.team.Player;
import lesson_1_OOP_and_Number_of_strings.oopTask.team.Team;

public class Course {//Полоса препятствий

    Barier[] course = new Barier[3]; //Массив препятствий

    public Course(int crossDistanceLength, int poolLength, int wallHeight){ //Передаем значения заданные пользователем

        if (crossDistanceLength > 0)                                        //Валидно?
            course[0] = new CrossDistance(crossDistanceLength);
        else                                                                //Ставим свое (не выводим никакого сообщения, но помним, что было бы хорошо вывести))
            course[0] = new CrossDistance(100);

        if (poolLength > 0)
            course[1] = new Pool(poolLength);
        else
            course[1] = new Pool(50);

        if(wallHeight > 0)
            course[2] = new Wall(wallHeight);
        else
            course[2] = new Wall(50);
    }


    /**
     * Информация о полосе препятствий
     */
    public void courseInfo(){
        System.out.println("\nИнформация о полосе препятствий:");
        for (Barier b: course){
            b.barierInfo();
        }
        System.out.println();
    }


    /**
     * Собственно команда проходит полосу препятствий
     * @param team
     */
    public void doIt(Team team){
        for (Player p : team.getTeamArr()){
            for (Barier b : course){
                if (p.isOnDistance())
                    b.doit(p);
            }
        }
    }
}
