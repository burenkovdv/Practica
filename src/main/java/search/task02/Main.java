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

        int start = 0;
        int end = list.size();
        int mid = end / 2;

        for (int i = 0; i < end; i++) {
            if(list.get(mid)==key){
                return mid;
            } else if (key.compareTo(list.get(mid))<0) {
                end=mid;
                mid=end/2+1;
            }
            else {
                start=mid;
                mid=mid+(end-start)/2;
            }
        }
            return (mid+2)*-1;
    }
}
