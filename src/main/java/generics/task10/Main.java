package generics.task10;

import java.util.*;


public class Main {
    public static void main(String[] args) {

        // Пример 1: сортировка целых чисел
        List<Integer> numbers = Arrays.asList(5, 2, 9, 1, 7);
        List<Integer> sortedNumbers = sortedCopy(numbers);
        System.out.println("Оригинал: " + numbers);
        System.out.println("Отсортированная копия: " + sortedNumbers);

        // Пример 2: сортировка строк
        List<String> words = Arrays.asList("banana", "apple", "cherry", "date");
        List<String> sortedWords = sortedCopy(words);
        System.out.println("Оригинал: " + words);
        System.out.println("Отсортированная копия: " + sortedWords);

    }

    /**
     * Возвращает **новый** список, содержащий все элементы входного списка,
     * но отсортированный в порядке возрастания по их естественному сравнению.
     *
     * @param list исходный список элементов
     * @param <T>  тип элементов, реализующий Comparable<? super T>
     * @return новый ArrayList<T> с теми же элементами, но в отсортированном порядке
     */
    public static <T extends Comparable<? super T>> List<T> sortedCopy(List<? extends T> list) {
        List<T> result = new ArrayList<>();
        for (T element : list) {
                result.add(element);
        }
        Collections.sort(result);
        return result;
    }
}
