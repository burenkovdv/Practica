package generics.task02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(1, 2, 10, 3, 4));
        swap(list, 0, 3);
        System.out.println(list);

        System.out.println(findMax(list));
    }

    /**
     * Обменивает элементы с индексами i и j в списке list.
     *
     * @param list список любого типа
     * @param i    первый индекс
     * @param j    второй индекс
     * @param <T>  тип элементов списка
     */
    public static <T> void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    /**
     * Собирает все аргументы в новый список.
     *
     * @param items набор элементов любого типа
     * @param <T>   тип элементов
     * @return новый ArrayList, содержащий все items в порядке передачи
     */
    public static <T> List<T> toList(T... items) {
        return Arrays.asList(items);
    }


    /**
     * Ищет наибольший элемент в коллекции по естественному порядку.
     *
     * @param coll коллекция объектов, сравнимых между собой
     * @param <T>  тип, реализующий Comparable<T>
     * @return наибольший элемент или null, если коллекция пуста
     */
    public static <T extends Comparable<T>> T findMax(Collection<T> coll) {
        T max = null;
        for (T elem : coll) {
            if (max == null) {
                max = elem;
                continue;
            }
            if (elem.compareTo(max) > 0) {
                max = elem;
            }
        }
        return max;
    }


    /**
     * Ищет первый индекс в списке, по которому стоит элемент, равный заданному.
     *
     * @param list    коллекция элементов типа T
     * @param element искомый элемент (сравнение через equals)
     * @param <T>     тип элементов
     * @return индекс первого вхождения element в list, или -1 если не найден
     */
    public static <T> int indexOf(List<T> list, T element) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(element)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Возвращает новый список, содержащий все элементы из first, а затем все из second.
     *
     * @param first  первый список (может быть List<Sub>, где Sub extends T)
     * @param second второй список
     * @param <T>    общий базовый тип элементов
     * @return новый ArrayList<T> с элементами first + second
     */
    public static <T> List<T> concat(List<? extends T> first, List<? extends T> second) {
        List<T> result = new ArrayList<>();
        result.addAll(first);
        result.addAll(second);
        return result;
    }

    /**
     * Возвращает новый список, содержащий все элементы src,
     * которые больше заданного порога (compareTo > 0).
     *
     * @param src       коллекция элементов типа T (T реализует Comparable<T>)
     * @param threshold пороговое значение
     * @param <T>       тип, реализующий Comparable<T>
     * @return список элементов из src, больших threshold
     */
    public static <T extends Comparable<T>> List<T> filterGreaterThan(Collection<? extends T> src, T threshold) {
        List<T> result = new ArrayList<>();
        for (T element : src) {
            if (element.compareTo(threshold) > 0) {
                result.add(element);
            }

        }
        return result;
    }


}
