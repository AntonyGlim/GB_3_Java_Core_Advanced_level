package lesson_1_OOP_and_Number_of_strings.oopTask.team;

public class Team {

    String teamName;                                            //Имя команды
    Player[] teamArr = new Player[4];                           //Массив игроков

    public Team(String teamName, String catName, String dogName, String badgerName, String humanName){ //Задаем только имена, лимиты назначатся рандомно
        this.teamName = teamName;
        teamArr[0] = new Cat(catName);                          //Создаем участников команды
        teamArr[1] = new Dog(dogName);
        teamArr[2] = new Badger(badgerName);
        teamArr[3] = new Human(humanName);
    }


    /**
     * Выводим информацию о всех участниках команды
     */
    public void teamAllMembersInfo(){
        System.out.println("\nИнформация о команде:");
        System.out.println("Название команды: " + teamName + ".");
        for (Player p : teamArr) {
            p.playerInfo();
        }
        System.out.println();                                   //Перенос строки, чтобы не сливался текст
    }


    /**
     * Выводим информацию о тех, кто прошел все препятствия
     */
    public void teamWinnersInfo(){
        System.out.println("\nИнформация о тех, кто прошел все препятствия:");
        boolean isThereAnyone = false;                          //Кто нибудь уже прошел марафон? нет
        for (Player p : teamArr) {
            if (p.onDistance){
                p.playerInfo();
                isThereAnyone = true;
            }
        }
        if (!isThereAnyone){                                    //Если никто не прошел марафон
            System.out.println("Никто не прошел марафон!");
        }
        System.out.println();                                   //Перенос строки, чтобы не сливался текст
    }


    /**
     * Возвращаем массив игроков (чтобы полоса препятствий могла обратиться)
     * @return
     */
    public Player[] getTeamArr(){
        Player[] arr = new Player[teamArr.length];
        for (int i = 0; i < teamArr.length ; i++) {
            arr[i] = teamArr[i];
        }
        return arr;
    }


}
