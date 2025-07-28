package search.task02;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> nums = List.of(1, 3, 5, 7, 9, 11);
        System.out.println(binarySearch(nums, 5));   // → 2
        System.out.println(binarySearch(nums, 6));   // → −4   (insertion point = 3)

        List<String> words = List.of("apple", "banana", "cherry", "date");
        System.out.println(binarySearch(words, "cherry")); // → 2
        System.out.println(binarySearch(words, "fig"));    // → −5

    }

    /**
     * Ищет элемент key в отсортированном списке list методом двоичного поиска.
     *
     * @param list отсортированный в порядке возрастания список элементов типа T или его подклассов
     * @param key  искомый элемент
     * @param <T>  тип, реализующий Comparable<? super T>
     * @return индекс найденного key в list, или −(insertionPoint)−1, если не найден
     */
    public static <T extends Comparable<? super T>> int binarySearch(
            List<? extends T> list,
            T key
    ) {

        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            T midVal = list.get(mid);
            int cmp = midVal.compareTo(key);
            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        // key not found => insertion point = low
        return -low - 1;
    }
}
