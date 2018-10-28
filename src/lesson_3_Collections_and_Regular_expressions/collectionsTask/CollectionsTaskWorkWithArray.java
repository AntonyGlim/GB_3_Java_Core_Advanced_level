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

        ArrayList<String> arrayList = new ArrayList<>(); 
        arrayList.add("азот");  arrayList.add("бинт");  arrayList.add("воин");
        arrayList.add("грот");  arrayList.add("дюйм");  arrayList.add("жанр");
        arrayList.add("кожа");  arrayList.add("кран");  arrayList.add("бинт");
        arrayList.add("грот");  arrayList.add("воин");  arrayList.add("воин");
        arrayList.add("воин");  arrayList.add("бинт");  arrayList.add("азот");

        System.out.println("Уникальные значения: ");
        uniqueWordsListInfo(arrayList);
        System.out.println("\nСколько раз встречается слово в листе: ");
        repeatedWordsNumberInfo(arrayList);
    }

    public static void uniqueWordsListInfo(ArrayList<String> arrayList){
        Set<String> setList = new HashSet();
        setList.addAll(arrayList);
        for (String s : setList){
            System.out.println(s);
        }
    }

    public static void repeatedWordsNumberInfo(ArrayList<String> arrayList){
        Map<String, Integer> mapList = new HashMap<>();
        for (String a : arrayList){
            Integer number = mapList.get(a);
            mapList.put(a, number == null ? 1 : number + 1);
        }
        System.out.println(mapList);
    }
}