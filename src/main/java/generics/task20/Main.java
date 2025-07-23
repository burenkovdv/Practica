package generics.task20;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;

public class Main {
    public static void main(String[] args) {
        List<Object> mixed = new ArrayList<>(List.of(
                "apple", 42, "banana", 3.14, "cherry", 99
        ));

        int removedStrings = removeAllInstancesOf(mixed, String.class);
        System.out.println(removedStrings); // → 3
        System.out.println(mixed);          // → [42, 3.14, 99]

        int removedIntegers = removeAllInstancesOf(mixed, Integer.class);
        System.out.println(removedIntegers); // → 2
        System.out.println(mixed);

    }


    /**
     * Создаёт новый список, объединяя элементы двух списков
     * с помощью функции zipFunction.
     * Итерация идёт до min(a.size(), b.size()).
     *
     * @param a           первый список элементов типа A
     * @param b           второй список элементов типа B
     * @param zipFunction функция, принимающая (A elemFromA, B elemFromB)
     *                    и возвращающая результат типа R
     * @param <A>         тип элементов первого списка
     * @param <B>         тип элементов второго списка
     * @param <R>         тип элементов результата
     * @return список из R длины min(a.size(), b.size())
     */
    public static <A, B, R> List<R> zipWith(
            List<? extends A> a,
            List<? extends B> b,
            BiFunction<? super A, ? super B, ? extends R> zipFunction
    ) {
        List<R> result = new ArrayList<>();
        int count = Math.min(a.size(), b.size());
        for (int i = 0; i < count; i++) {
            result.add(zipFunction.apply(a.get(i),b.get(i)));
        }
        return result;
    }


    /**
     * Удаляет из списка все элементы, являющиеся экземплярами указанного класса.
     *
     * @param list  список, способный хранить объекты любых суперклассов T
     * @param clazz класс, описывающий тип элементов, которые нужно удалить
     * @param <T>   тип, на который мы проверяем экземпляры
     * @return      количество удалённых элементов
     */
    public static <T> int removeAllInstancesOf(
            List<? super T> list,
            Class<T> clazz
    ){
        int count = 0;
        Iterator<? super T> iterator = list.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            if(clazz.isInstance(next)) {
                iterator.remove();
                count++;
            }
        }
        return count;
    }


}
