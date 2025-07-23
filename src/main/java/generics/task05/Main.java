package generics.task05;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        List<String> words = List.of("apple", "ant", "banana", "ball", "apple", "ant");

        // Подсчёт по первой букве
        Map<Character, Long> counts = countBy(words, word -> word.charAt(0));
        System.out.println(counts);
        // → {a=4, b=2}

        // Подсчёт слов по длине
        Map<Integer, Long> lenCounts = countBy(words, new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        });
        System.out.println(lenCounts);
        // → {5=2, 3=2, 6=2}

    }

    /**
     * Подсчитывает, сколько раз встречается каждый ключ, извлечённый из элементов списка.
     *
     * @param list          исходный список элементов типа T
     * @param keyExtractor  функция, извлекающая ключ типа K из элемента
     * @param <T>           тип элементов списка
     * @param <K>           тип ключа
     * @return              Map, где ключ — результат keyExtractor, а значение — количество таких элементов
     */
    public static <T, K> Map<K, Long> countBy(List<T> list, Function<? super T, ? extends K> keyExtractor){
        LinkedHashMap<K, Long> result = new LinkedHashMap<>();
        for (T elem : list) {
            K key = keyExtractor.apply(elem);
            if(result.containsKey(key)){
                result.put(key, result.get(key) + 1);
            } else {
                result.put(key, 1L);
            }
        }
        return result;

    }

}
