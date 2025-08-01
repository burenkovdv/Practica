package data_structures.task03;

import java.util.NoSuchElementException;

public class BoundedQueue<T> {
    private final T[] data;
    private int head = 0;
    private int tail = 0;
    private int size = 0;

    /**
     * Создаёт очередь с заданной максимальной ёмкостью.
     *
     * @param capacity ёмкость (> 0)
     * @throws IllegalArgumentException если capacity <= 0
     */
    @SuppressWarnings("unchecked")
    public BoundedQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero");
        }
        this.data = (T[]) new Object[capacity];
    }

    /**
     * @return текущее число элементов
     */
    public int size() {
        return size;
    }

    /**
     * @return true, если очередь пуста
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return true, если очередь заполнена по ёмкости
     */
    public boolean isFull() {
        return size == data.length;
    }

    /**
     * Добавляет элемент в конец очереди.
     *
     * @throws IllegalStateException если очередь полна
     */
    public void enqueue(T element) {
        if (isFull()) {
            throw new IllegalStateException("Очередь полная");
        }
        data[tail] = element;
        size++;
        tail++;
        if (tail == data.length) {
            tail = 0;
        }
    }

    /**
     * Удаляет и возвращает элемент из начала очереди.
     *
     * @return элемент из начала
     * @throws NoSuchElementException если очередь пуста
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Очередь пуста");
        }
        T element = data[head];
        data[head] = null;
        size--;
        head++;
        if (head == data.length) {
            head = 0;
        }
        return element;
    }

    /**
     * Возвращает (не удаляя) элемент из начала очереди.
     *
     * @return элемент из начала
     * @throws NoSuchElementException если очередь пуста
     */
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Очередь пуста");
        }
        return data[head];
    }
}

