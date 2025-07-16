package generics.task04;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {

            String a = null;
            String b = "hello";
            String c = "world";

        //   System.out.println(firstNonNull(a, b, c)); // → hello
        //System.out.println(firstNonNull(null, null, null)); // → null

        List<Object> test = new ArrayList<>();
        test.add(1);
        test.add("rrrr");

        System.out.println(test);



        List<String> words = List.of("apple", "banana", "kiwi", "pear", "grape");

        List<String> longWords = filter(words, word -> word.length() > 4);
        System.out.println(longWords); // → [apple, banana, grape]

        List<Integer> numbers = List.of(1, -2, 3, -4, 5);

        List<Integer> positives = filter(numbers, n -> n > 0);
        System.out.println(positives); // → [1, 3, 5]



    }

    /**
     * Извлекает из списка все элементы заданного типа.
     *
     * @param list  исходный список, содержащий объекты любых типов
     * @param clazz класс, описывающий искомый тип
     * @param <T>   тип, на который мы фильтруем
     * @return      новый список, содержащий только те элементы из list,
     *              которые являются экземплярами clazz, приведённые к T
     */
    public static <T> List<T> filterByType(List<?> list, Class<T> clazz){
        List<T> result = new ArrayList<>();
        for (Object o : list){
            if (clazz.isInstance(o)){result.add(clazz.cast(o));}
        }
        return result;
    }

    public static <T> T firstNonNull(T... items) {
        for (T item : items) {
            if (item != null) return item;
        }
        return null;
    }

    public static <T> List<T> filterByTypeAndPredicate(List<?> list, Class<T> clazz, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (Object obj : list) {
            if (clazz.isInstance(obj)) {
                T item = clazz.cast(obj);
                if (predicate.test(item)) {
                    result.add(item);
                }
            }
        }
        return result;
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate){
        List<T> result = new ArrayList<>();
        for (T element : list) {
                if(predicate.test(element)){result.add(element);}
        }


        return result;
    }


}
