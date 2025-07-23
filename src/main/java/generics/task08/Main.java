package generics.task08;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        SimpleQueue<String> p1 = new SimpleQueue<>();
        SimpleQueue<String> p2 = new SimpleQueue<>();
        p1.enqueue("red");
        p1.enqueue("green");
        p1.enqueue("yellow");

        System.out.println(p1);
        SimpleQueue.transfer(p2,p1);

        System.out.println(p1);
        System.out.println(p2);


    }
}

class SimpleQueue<T> {
    private T[] items;

    public SimpleQueue() {
        this.items = (T[]) new Object[0];
    }


    //Добовляем элемент в массив
    void enqueue(T item) {
        T[] result = Arrays.copyOf(items, items.length + 1);
        result[result.length - 1] = item;
        items = result;
    }

    public String getItems() {
        return Arrays.toString(items);
    }

    //Удаляем последний элемент из массива и возвращаем его
    T dequeue() {
        if (items.length == 0) throw new NoSuchElementException("Массив пустой");
        T head = items[0];
        items = Arrays.copyOfRange(items, 1, items.length);
        return head;
    }

    //Возвращает последний элемент массива
    T peek() {
        if (items.length == 0) throw new NoSuchElementException("Массив пустой");
        return items[0];
    }

    //Проверяем есть ли в массиве элементы
    boolean isEmpty() {
        return items.length == 0;
    }

    //Возвращаем текущее числов элементов в стэке
    int size() {
        return items.length;
    }

    public String toString() {
        return Arrays.toString(items);
    }

    public static <T> void transfer(SimpleQueue<? super T> dest, SimpleQueue<? extends T> src) {
        while (!src.isEmpty())
            dest.enqueue(src.dequeue());
    }


}


