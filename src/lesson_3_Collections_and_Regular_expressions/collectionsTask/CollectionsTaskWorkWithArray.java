/**
 *  1. Создать массив с набором слов (10-20 слов, среди которых должны встречаться повторяющиеся).
 *  Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
 *  Посчитать, сколько раз встречается каждое слово.
 *
 */
package lesson_3_Collections_and_Regular_expressions.collectionsTask;

import java.sql.Array;
import java.util.*;

public class CollectionsTaskWorkWithArray {
    public static void main(String[] args) {

        String[] arr = {"азот", "азот", "бинт", "бинт", "бинт", "грот", "грот", "дюйм",
                        "жанр", "кран", "кожа", "воин", "воин", "воин", "воин", "воин"};

        System.out.println("Уникальные значения: ");
        uniqueWordsListInfo(arr);
        System.out.println("\nСколько раз встречается слово в листе: ");
        repeatedWordsNumberInfo(arr);
    }

    public static void uniqueWordsListInfo(String[] arr){
        Set<String> setList = new HashSet();
        setList.addAll(new ArrayList<>(Arrays.asList(arr)));
        for (String s : setList){
            System.out.println(s);
        }
    }

    public static void repeatedWordsNumberInfo(String[] arr){
        Map<String, Integer> mapList = new HashMap<>();
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(arr));
        for (String a : arrayList){
            Integer number = mapList.get(a);
            mapList.put(a, number == null ? 1 : number + 1);
        }
        System.out.println(mapList);
    }
}