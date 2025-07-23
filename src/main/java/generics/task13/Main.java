package generics.task13;

import java.util.List;

public class Main {

    /**
     * Копирует все элементы из src в dest.
     *
     * @param dest список, способный принять элементы типа T или их супертипов
     * @param src  список, производящий элементы типа T или их подтипов
     * @param <T>  тип элементов
     */
    public static <T> void copyAll(List<? super T> dest, List<? extends T> src) {
        dest.addAll(src);
    }

    /**
     * Ищет, встречается ли value в любом списке списков.
     *
     * @param lists коллекция списков, каждый из которых может содержать T или его подтипы
     * @param value искомый элемент
     * @param <T>   тип элемента
     * @return true, если value найден в любом из вложенных списков
     */
    public static <T> boolean containsInAny(List<? extends List<? extends T>> lists, T value) {
        for (List<? extends T> list : lists) {
            if (list.contains(value)) {
                return true;
            }
        }

        return false;
    }


    /**
     * Добавляет в target только те элементы из source, которых в target ещё нет.
     *
     * @param target список, потребляющий T или его супертипы
     * @param source список, производящий T или его подтипы
     * @param <T>    тип элементов
     */
    public static <T> void addIfAbsent(
            List<? super T> target,
            List<? extends T> source
    ) {
        for (T elem : source) {
            if (!target.contains(elem)) {
                target.add(elem);
            }
        }
    }


}
