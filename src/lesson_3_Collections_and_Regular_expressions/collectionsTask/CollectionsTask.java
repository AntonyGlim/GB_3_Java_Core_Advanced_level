/**
 * 1. Создать массив с набором слов (10-20 слов, среди которых должны встречаться повторяющиеся).
 * Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
 * Посчитать, сколько раз встречается каждое слово.
 *
 * 2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
 * В этот телефонный справочник с помощью метода add() можно добавлять записи.
 * С помощью метода get() искать номер телефона по фамилии. Следует учесть,
 * что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
 * тогда при запросе такой фамилии должны выводиться все телефоны.
 *
 * Желательно как можно меньше добавлять своего, чего нет в задании
 * (т.е. не надо в телефонную запись добавлять еще дополнительные поля (имя, отчество, адрес),
 * делать взаимодействие с пользователем через консоль и т.д.).
 * Консоль желательно не использовать (в том числе Scanner),
 * тестировать просто из метода main(), прописывая add() и get().
 */

package lesson_3_Collections_and_Regular_expressions.collectionsTask;

import java.util.*;

public class CollectionsTask {
    public static void main(String[] args) {

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("азот");  arrayList.add("бинт");  arrayList.add("воин");
        arrayList.add("грот");  arrayList.add("дюйм");  arrayList.add("жанр");
        arrayList.add("кожа");  arrayList.add("кран");  arrayList.add("бинт");
        arrayList.add("грот");  arrayList.add("воин");  arrayList.add("воин");
        arrayList.add("воин");  arrayList.add("бинт");  arrayList.add("азот");

        uniqueWordsListInfo(arrayList);
        repeatedWordsNumberInfo(arrayList);

        Map<String, String> phoneBook = new HashMap<>();

        phoneBook.put("3-32-76", "Иванов");
        phoneBook.put("3-32-79", "Петров");
        phoneBook.put("3-32-12", "Сидоров");
        phoneBook.put("3-32-02", "Ким");
        phoneBook.put("3-32-17", "Заболотный");
        phoneBook.put("3-32-84", "Сидоров");
        phoneBook.put("3-32-25", "Иванов");
        phoneBook.put("3-32-03", "Иванов");

        System.out.println(phoneBook.get("3-32-03"));
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

class Phonebook{

    public void Phonebook(){
        Map<String, String> phoneBook = new HashMap<>();

        phoneBook.put("3-32-76", "Иванов");
        phoneBook.put("3-32-79", "Петров");
        phoneBook.put("3-32-12", "Сидоров");
        phoneBook.put("3-32-02", "Ким");
        phoneBook.put("3-32-17", "Заболотный");
        phoneBook.put("3-32-84", "Сидоров");
        phoneBook.put("3-32-25", "Иванов");
        phoneBook.put("3-32-03", "Иванов");

        System.out.println(phoneBook.get("Иванов"));
    }

}
