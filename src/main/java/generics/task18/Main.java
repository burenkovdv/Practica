package generics.task18;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        List<Object> mixed = List.of("apple", 42, "banana", 3.14, 7);
        List<String> strings = filterByType(mixed, String.class);
        System.out.println(strings); // [apple, banana]

        List<Integer> ints = filterByType(mixed, Integer.class);
        System.out.println(ints); // [42, 7]


    }

    public static <T> void replaceAllOccurrences(
            List<? super T> list,
            T oldValue,
            T newValue
    ) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(oldValue)) {
                list.set(i, newValue);
            }
        }

    }

    public static <T> void mergeDistinct(
            List<? super T> target,
            List<? extends T> source
    ) {
        for (T element : source) {
            if (!target.contains(element)) {
                target.add(element);
            }
        }

    }

    public static <T> void copyIfInstanceOf(
            List<?> source,
            List<? super T> target,
            Class<T> clazz
    ) {
        for (Object element : source) {
            if (clazz.isInstance(element)) {
                target.add((T) element);
            }
        }
    }

    public static <T> List<T> filterByType(List<?> list, Class<T> clazz) {
        List<T> result = new ArrayList<>(list.size());
        for (Object object : list) {
            if (clazz.isInstance(object)) {
                result.add((T)object);
            }
        }
        return result;
    }
}
