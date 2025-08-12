package lambda_streamapi.task04;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> words = List.of("apple", "bee", "car", "door", "elephant", "fox");
        List<Integer> list = filterAndTransform(words, x -> x.length() <= 3, String::length);
        list.forEach(System.out::println);

        List<String> list1 = filterAndTransform(words, x -> x.charAt(0) == 'b' || x.charAt(0) == 'f',
                String::toUpperCase);
        list1.forEach(System.out::println);
    }

    public static <T, R> List<R> filterAndTransform(
            List<T> list,
            java.util.function.Predicate<T> filter,
            java.util.function.Function<T, R> transformer
    ) {
        List<R> result = new ArrayList<>();

        for (T t : list) {
            if (filter.test(t)) {
                result.add(transformer.apply(t));
            }
        }
        return result;
    }

}
