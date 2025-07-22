package generics.task11;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<List<Integer>> nested = List.of(
                List.of(1, 2),
                List.of(3),
                List.of(4, 5, 6)
        );


        List<Integer> flat = flatten(nested);
        System.out.println(flat); // [1, 2, 3, 4, 5, 6]

        List<String> data = new ArrayList<>() {{
            add("apple");
            add(null);
            add("banana");
            add(null);
            add("cherry");
        }};

        List<String> result = copyNonNull(data);
        System.out.println(result); // [apple, banana, cherry]


    }

    public static <T> List<T> flatten(List<List<T>> nestedLists) {
        List<T> result = new ArrayList<>();
        for (List<T> element : nestedLists) {
            for (T item : element) {
                result.add(item);
            }
        }
        return result;
    }

    public static <T> List<T> copyNonNull(List<T> list) {
        List<T> result = new ArrayList<>();
        for (T element : list) {
            if (element != null) {
                result.add(element);
            }
        }
        return result;
    }


}
