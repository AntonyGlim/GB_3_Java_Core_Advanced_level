package lesson_1_OOP_and_Number_of_strings.oopTask.team;

public class Player {

    String type;                                    //Биологический вид участника
    String name;                                    //Имя

    int maxRunDistance;                             //Предел по бегу генериуем случайно в подклассах для каждого вида диапазон значений будет отличаться
    int maxSwimDistance;                            //Предел по плаванью
    int maxJumpHeight;                              //Предел ро прыжкам

    boolean onDistance;                             //Животное не выбыло?


    public Player(String type, String name){
        this.type = type;
        this.name = name;
        onDistance = true;
    }


    /**
     * Выводим информацию об участнике и его возможности
     */
    public void playerInfo(){
        System.out.println(type + " " + name +
                " сможет пробежать: " + maxRunDistance + " м." +
                ", сможет проплыть: " + maxSwimDistance + " м." +
                ", подпрыгнуть на: " + maxJumpHeight + " cм." +
                " Он на дистанции: " + onDistance + ".");
    }


    /**
     * Участник бежит
     * @param dist - заданная нами вдистанция
     */
    public void run(int dist) {
        if(dist <= maxRunDistance) {
            System.out.println(type + " " + name + " пробежал " + dist + " м., и справился с кроссом");
        } else {
            System.out.println(type + " " + name + " не пробежал " + dist + " м., и не справился с кроссом. Он покидает дистанцию!");
            onDistance = false;
        }
    }


    /**
     * Участник плывет
     * @param dist - заданная нами вдистанция
     */
    public void swim(int dist) {
        if(dist <= maxSwimDistance) {
            System.out.println(type + " " + name + " проплыл " + dist + " м., и справился с заплывом");
        } else {
            System.out.println(type + " " + name + " не проплыл " + dist + " м., и не справился с заплывом. Он покидает дистанцию!");
            onDistance = false;
        }
    }


    /**
     * Участник прыгает
     * @param height - заданная нами высота
     */
    public void jump(int height) {
        if(height <= maxJumpHeight) {
            System.out.println(type + " " + name + " подпрыгнул на " + height + " см., и справился с прыжком");
        } else {
            System.out.println(type + " " + name + " не подпрыгнул на " + height + " см., и не справился с прыжком. Он покидает дистанцию!");
            onDistance = false;
        }
    }


    /**
     * Животное не выбыло?
     * @return да/нет
     */
    public boolean isOnDistance() {
        return onDistance;
    }


    /**
     * Возвращает случайное целое число из заданного диапазона TODO Как вынести этот метод в отдельный класс, чтобы к нему обращаться?
     * @param min
     * @param max
     * @return
     */
    public int rnd(int min, int max){
        int result = (int) (Math.random() * (max - min + 1) + min);
        return result;
    }

}
