package generics.task15;

import java.util.*;
import java.util.function.BiFunction;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> map1 = Map.of("a", 1, "b", 2, "c", 3);
        Map<String, Integer> map2 = Map.of("b", 10, "c", 20, "d", 30);

        // суммируем значения по совпадающим ключам
        Map<String, Integer> sumMerge = mergeWith(
                map1,
                map2,
                Integer::sum
        );
        // ожидаем: {a=1, b=12, c=23, d=30}
        System.out.println(sumMerge);

        // берём максимум
        Map<String, Integer> maxMerge = mergeWith(
                map1,
                map2,
                Math::max
        );
        // ожидаем: {a=1, b=10, c=20, d=30}
        System.out.println(maxMerge);

        List<Integer> list1 = List.of(1, 2, 3, 4, 5, 6);
        Iterator<Integer> iterator = list1.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next % 2 == 0) {
                iterator.remove();
            }
        }
        System.out.println(list1);
    }

    /**
     * Объединяет две карты. Для каждого ключа:
     * - Если он присутствует только в одной из карт, в результат попадает его значение.
     * - Если в обеих — значения объединяются через combiner.
     *
     * @param m1       первая карта ключ→значение
     * @param m2       вторая карта ключ→значение
     * @param combiner функция комбинирования двух значений одного ключа
     * @param <K>      тип ключей
     * @param <V>      тип значений
     * @param <R>      тип результирующих значений
     * @return новая LinkedHashMap<K,R> в порядке вставки ключей из m1, затем ключей из m2, которых не было в m1
     */
    public static <K, V, R> Map<K, R> mergeWith(
            Map<K, V> m1,
            Map<K, V> m2,
            BiFunction<? super V, ? super V, ? extends R> combiner
    ) {
        HashSet<K> keys = new HashSet<>(m1.keySet());
        keys.addAll(m2.keySet());
        LinkedHashMap<K, R> result = new LinkedHashMap<>();
        for (K key : keys) {
            if (m1.containsKey(key) && m2.containsKey(key)) {
                R value = combiner.apply(m1.get(key), m2.get(key));
                result.put(key, value);
            } else if (m1.containsKey(key)) {
                result.put(key, (R) m1.get(key));
            } else {
                result.put(key, (R) m2.get(key));
            }
        }

        return result;


    }

}
