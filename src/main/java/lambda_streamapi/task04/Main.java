package lambda_streamapi.task04;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        List<String> words = List.of("apple", "bee", "car", "door", "elephant", "fox");
        List<Integer> list = filterAndTransform(words, x -> x.length() <= 3, String::length);
        list.forEach(System.out::println);

        List<String> list1 = filterAndTransform(words, x -> !x.substring(0, 1).contains("b") && !x.substring(0, 1).contains("f"),
                String::toUpperCase);
        list1.forEach(System.out::println);


    }

    public static <T, R> List<R> filterAndTransform(
            List<T> list,
            java.util.function.Predicate<T> filter,
            java.util.function.Function<T, R> transformer
    ) {
        List<T> result = new ArrayList<>(list);
        result.removeIf(filter);
        Function<T, R> func = transformer;
        List<R> result1 = new ArrayList<>();

        for (T element : result) {
            result1.add(func.apply(element));
        }
        return result1;
    }

}
