package lesson_3_Collections_and_Regular_expressions.collectionsTask;

import java.util.HashMap;
import java.util.Map;

public class CollectionsTaskPhonebook {
    public static void main(String[] args) {
        Map<String, String> phoneBook = new HashMap<>();


        phoneBook.put("3-32-76", "Иванов");
        phoneBook.put("3-32-79", "Петров");
        phoneBook.put("3-32-12", "Сидоров");
        phoneBook.put("3-32-02", "Ким");
        phoneBook.put("3-32-17", "Заболотный");
        phoneBook.put("3-32-84", "Сидоров");
        phoneBook.put("3-32-25", "Иванов");
        phoneBook.put("3-32-03", "Иванов");


        for (Map.Entry<String, String> o : phoneBook.entrySet()){
            if (o.getValue() == "Иванов"){
                System.out.print("По запросу " + o.getValue() + "Найдено: " + o.getKey());
            }
        }
    }
}
