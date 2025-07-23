package generics.task06;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        // Пример 1: из списка строк делаем карту string → его длина
        List<String> words = List.of("apple", "banana", "pear");
        Map<String, Integer> map1 = toMap(words,
                Function.identity(),      // ключ = сама строка
                new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) {
                        return s.length();
                    }
                }            // значение = длина строки
        );
        System.out.println(map1);
        // → {apple=5, banana=6, pear=4}

        // Пример 2: из списка пользователей делаем карту id → имя
        List<User> users = List.of(
                new User(1, "Alice"),
                new User(2, "Bob"),
                new User(3, "Carol")
        );
        Map<Integer, String> map2 = toMap(users,
                User::getId,
                User::getName
        );
        System.out.println(map2);
        // → {1=Alice, 2=Bob, 3=Carol}
    }



    /**
     * Возвращает новую карту, в которой каждому ключу из исходной карты
     * соответствует значение, полученное применением функции mapper к старому значению.
     *
     * @param sourceMap исходная карта ключ→значение
     * @param mapper    функция преобразования значений V1→V2
     * @param <K>       тип ключей
     * @param <V1>      исходный тип значений
     * @param <V2>      новый тип значений
     * @return          новая карта ключ→преобразованное_значение
     */
    public static <K, V1, V2> Map<K, V2> mapValues(
            Map<K, V1> sourceMap,
            Function<? super V1, ? extends V2> mapper
    ){
        Map<K, V2> result = new HashMap<>();
        for (K key : sourceMap.keySet()) {
            V1 oldValue = sourceMap.get(key);
            V2 newValue = mapper.apply(oldValue);
            result.put(key,newValue);

        }

        return result;
    }

    /**
     * Преобразует список элементов в карту, где:
     * - ключ для каждого элемента вычисляется функцией keyMapper,
     * - значение для каждого элемента — функцией valueMapper.
     *
     * @param list        исходный список элементов типа T
     * @param keyMapper   функция, возвращающая ключ типа K для элемента
     * @param valueMapper функция, возвращающая значение типа V для элемента
     * @param <T>         тип элементов списка
     * @param <K>         тип ключей в результирующей карте
     * @param <V>         тип значений в результирующей карте
     * @return            Map, где каждому ключу соответствует результат valueMapper
     */
    public static <T, K, V> Map<K, V> toMap(
            List<T> list,
            Function<? super T, ? extends K> keyMapper,
            Function<? super T, ? extends V> valueMapper
    ){
        LinkedHashMap<K, V> result = new LinkedHashMap<>();
        for (T item : list) {
            K key = keyMapper.apply(item);
            V value = valueMapper.apply(item);
            result.put(key,value);
        }

        return result;
    }


}

class User {
    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}