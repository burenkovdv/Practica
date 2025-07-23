package generics.task17;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> ints = new ArrayList<>(List.of(1, 5, 3, 7, 2));
        removeGreaterThan(ints, 5);
        System.out.println(ints);
        // → [1, 5, 3, 2]

        List<Number> nums = new ArrayList<>(List.of(2.5, 10, 5.0, 7.3));
        removeGreaterThan(nums, 6.0);
        System.out.println(nums);
        // → [2.5, 5.0]

    }

    /**
     * Удаляет из списка все элементы, которые строго больше заданного порогового значения.
     *
     * @param list      список, потребляющий элементы типа T или их супертипы
     * @param threshold пороговое значение
     * @param <T>       тип элементов, реализующий Comparable<? super T>
     */
    public static <T extends Comparable<? super T>> void removeGreaterThan(
            List<? super T> list,
            T threshold
    ) {

        Iterator<? super T> iterator = list.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            if (((T) next).compareTo(threshold) > 0) {
                iterator.remove();
            }
        }


    }


}
