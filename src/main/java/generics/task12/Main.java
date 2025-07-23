package generics.task12;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> ages = Map.of(
                "Alice", 30,
                "Bob",   25,
                "Carol", 28
        );

        // «Alice=30», «Bob=25», «Carol=28»
        List<String> formatted = mapEntries(
                ages,
                (name, age) -> name + "=" + age
        );
        System.out.println(formatted);

        // true, false, false
        List<Boolean> seniorFlags = mapEntries(
                ages,
                (name, age) -> age >= 30
        );
        System.out.println(seniorFlags);
    }

    // {1:"value1", 2:"value2", ...}
    /**
     * Для каждой пары «ключ→значение» из исходной карты вызывает mapper и
     * собирает результаты в новый список.
     *
     * @param map    исходная карта
     * @param mapper функция, принимающая ключ и значение, возвращающая объект R
     * @param <K>    тип ключей
     * @param <V>    тип значений
     * @param <R>    тип результата
     * @return       список R, полученных из всех записей карты
     */
    public static <K, V, R> List<R> mapEntries(
            Map<K, V> map,
            BiFunction<? super K, ? super V, ? extends R> mapper
    ) {
        List<R> result = new ArrayList<>();
        for (Map.Entry<K, V> entry : map.entrySet()) {
              result.add(mapper.apply(entry.getKey(),entry.getValue()));
        }
        return result;
    }


}
