package generics.task07;

import java.util.Arrays;
import java.util.NoSuchElementException;


public class Main {
    public static void main(String[] args) {

        SimpleStack<String> p1 = new SimpleStack<>();
        SimpleStack<Integer> q1 = new SimpleStack<>();

        System.out.println(p1.isEmpty());
        p1.push("Red");
        p1.push("green");
        q1.push(3);
        q1.push(10);
        q1.push(8);

        System.out.println(q1.getItems());
        System.out.println(q1.pop());
        System.out.println(q1.getItems());


    }

    static class SimpleStack<T> {
        private T[] items;

    public SimpleStack() {
        this.items = (T[]) new Object[0];
    }


        //Добовляем элемент в массив
        void push(T item) {
            T[] result = Arrays.copyOf(items, items.length + 1);

            result[result.length - 1] = item;
            items = Arrays.copyOf(result, result.length);

        }

        public String getItems() {
            return Arrays.toString(items);
        }

        //Удаляем последний элемент из массива и возвращаем его
        T pop() {
            if (items.length == 0) throw new NoSuchElementException("Массив пустой");
            T res = items[items.length - 1];
            T[] result = Arrays.copyOf(items, items.length - 1);
            items = Arrays.copyOf(result, result.length);
            return res;
        }

        //Возвращает последний элемент массива
        T peek() {
            if (items.length == 0) throw new NoSuchElementException("Массив пустой");
            return items[items.length - 1];
        }

        //Проверяем есть ли в массиве элементы
        boolean isEmpty() {
            return items.length > 0 ? false : true;
        }

        //Возвращаем текущее числов элементов в стэке
        int size() {
            return items.length;
        }

    }

}
