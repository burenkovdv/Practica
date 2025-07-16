package generics.task03;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        List<Integer> x = List.of(1, 4, 7, 9);
        List<Integer> y = List.of(2, 3, 5, 8, 10);
        //System.out.println(mergeSorted(x, y));
        System.out.println(mergeSorted(y, x));
        // → [1, 2, 3, 4, 5, 7, 8, 9, 10]

        List<String> s1 = List.of("apple", "banana", "date");
        List<String> s2 = List.of("apricot", "cherry", "elderberry");
        System.out.println(mergeSorted(s1, s2));
        // → [apple, apricot, banana, cherry, date, elderberry]


    }

    /**
     * Вычисляет сумму всех элементов в коллекции чисел.
     *
     * @param numbers коллекция любых подклассов Number
     * @return сумма как double
     */
    public static double sumNumbers(Collection<? extends Number> numbers) {
        double result = 0;
        for (Number number : numbers) {
            result += number.doubleValue();
        }

        return result;
    }

    /**
     * Вычисляет среднее арифметическое всех элементов в коллекции чисел.
     *
     * @param numbers коллекция любых подклассов Number
     * @return среднее как double, или 0.0, если коллекция пустая
     */
    public static double averageNumbers(Collection<? extends Number> numbers) {
                    double result = 0;
                    int counter = 0;
                    for (Number number : numbers) {
                        counter++;
                        result += number.doubleValue();
                    }
                    return result / counter;
    }

    public static double sumPositiveNumbers(Collection<? extends Number> numbers){
        double result = 0;
        for (Number number : numbers) {
             if (number.doubleValue() > 0) {result += number.doubleValue();}
        }
        return result;
    }

    /**
     * Ищет элемент key в отсортированном списке list методом бинарного поиска.
     *
     * @param list отсортированный в возрастании список элементов типа T или его подклассов
     * @param key  искомый элемент
     * @param <T>  тип, реализующий Comparable<? super T>
     * @return индекс первого найденного совпадения key в list, или -1, если не найден
     */
    public static <T extends Comparable<? super T>> int binarySearch(List<? extends T> list, T key){
      int result = 0;
      int q=0;
        for (T t : list) {
            if (t.compareTo(key) == 0) {return q;}
            q++;
        }
        return -1;
    }

    /**
     * Объединяет два отсортированных списка в один отсортированный список.
     *
     * @param a     первый отсортированный список (по возрастанию)
     * @param b     второй отсортированный список (по возрастанию)
     * @param <T>   тип элементов, реализующий Comparable<? super T>
     * @return      новый ArrayList, содержащий все элементы из a и b, также в порядке возрастания
     */
    public static <T extends Comparable<? super T>> List<T> mergeSorted(
            List<? extends T> a,
            List<? extends T> b
    ) {
        ArrayList<T> result = new ArrayList<>();
        int q = a.size();
        int j = b.size();
            if(q>j) {
                for (int i = 0; i < q; i++) {
                    if(i>j-1){result.add(a.get(i));} else {
                        if(a.get(i).compareTo(b.get(i))<0){
                            result.add(a.get(i));
                            result.add(b.get(i));
                        } else {result.add(b.get(i));
                            result.add(a.get(i));
                        }
                    }
                }
            }
            else {
                for (int i = 0; i < j; i++) {
                    if (i > q-1) {
                        result.add(b.get(i));
                    } else {
                        if (b.get(i).compareTo(a.get(i)) < 0) {
                            result.add(b.get(i));
                            result.add(a.get(i));
                        } else {
                            result.add(a.get(i));
                            result.add(b.get(i));
                        }
                    }
                }
            }

        return result;
    }


}
