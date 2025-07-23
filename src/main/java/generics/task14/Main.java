package generics.task14;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> ints = List.of(5, 2, 9, 1, 7);
        System.out.println(countLessThan(ints, 5));    // → 2  (элементы 2 и 1)

        List<String> words = List.of("apple", "banana", "cherry", "avocado", "aaa");
        System.out.println(countLessThan(words, "banana"));
        // → 2  ("apple" и "avocado" лексикографически меньше "banana")
    }

    /**
     * Считает, сколько элементов из списка меньше заданного порогового значения.
     *
     * @param list      отсортированный или неотсортированный список элементов, каждый из которых сравним
     *                  с порогом через Comparable
     * @param threshold пороговое значение
     * @param <T>       тип элементов, реализующий Comparable<? super T>
     * @return количество элементов list, для которых elem.compareTo(threshold) < 0
     */
    public static <T extends Comparable<? super T>> int countLessThan(
            List<? extends T> list,
            T threshold
    ) {
        int count = 0;
        for (T element : list) {
            if (element.compareTo(threshold) < 0) {
                count++;
            }
        }


        return count;
    }


}
