package generics.task07;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] x = {1, 8, 4, 2, 7};
        String[] str = {"red", "white", "green", "yellow"};
        SimpleStack<String> p1 = new SimpleStack<>(new String[]{"red", "white", "green", "yellow"});
        SimpleStack<String> p2 = new SimpleStack<>(str);
        SimpleStack<String> p3 = new SimpleStack<>();


    }

    class SimpleStack<T> {
        T[] items;


        public SimpleStack(T[] items) {
            this.items = Arrays.copyOf(items, items.length);
        }

        SimpleStack(){};



        void push(T item) {
            T[] result = Arrays.copyOf(items, items.length + 1);
            result[result.length - 1] = item;
            items = Arrays.copyOf(result, result.length);

        }

    }

}
