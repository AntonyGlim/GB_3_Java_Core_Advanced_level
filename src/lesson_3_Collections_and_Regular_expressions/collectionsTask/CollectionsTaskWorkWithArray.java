/**
 *  1. Создать массив с набором слов (10-20 слов, среди которых должны встречаться повторяющиеся).
 *  Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
 *  Посчитать, сколько раз встречается каждое слово.
 *
 */
package lesson_3_Collections_and_Regular_expressions.collectionsTask;

import java.util.*;

public class CollectionsTaskWorkWithArray {

    public static void main(String[] args) {
        String[] arr = {"азот", "азот", "бинт", "бинт", "бинт", "грот", "грот", "дюйм",
                        "жанр", "кран", "кожа", "воин", "воин", "воин", "воин", "воин"};    //Именно массив, как требует задание в нем 8 уникальных слов

        uniqueWordsListInfo(arr);                                                           //Список уникальных слов
        repeatedWordsNumberInfo(arr);                                                       //Количество повторений слов
    }


    /**
     * Метод выведет в консоль список уникальных значений массива
     * @param arr - массив слов
     */
    public static void uniqueWordsListInfo(String[] arr){
        Set<String> setList = new HashSet();                                                //Создаем HashSet()
        setList.addAll(new ArrayList<>(Arrays.asList(arr)));                                //Массив в List, List в Set (не самое логичное действие, но зато 1 строка)))
        System.out.println("Список уникальных слова: ");
        for (String s : setList){                                                           //Пользуемся свойством уникальности значений Set
            System.out.println(s);
        }
    }


    /**
     * Метод подсчитает и выведет в консоль, сколько раз встречается слово в массиве
     * @param arr - массив слов
     */
    public static void repeatedWordsNumberInfo(String[] arr){
        Map<String, Integer> mapList = new HashMap<>();                                     //Создаем HashMap()
        System.out.println("\nСколько раз встречается слово в листе: ");
        for (String a : arr){                                                               //Записываем элементы массива в HashMap
            Integer number = mapList.get(a);
            mapList.put(a, number == null ? 1 : number + 1);                                //Если такого элемента еще небыло
        }
        System.out.println(mapList);
    }
}