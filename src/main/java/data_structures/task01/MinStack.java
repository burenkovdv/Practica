package data_structures.task01;

import java.util.Stack;

public class MinStack<T extends Comparable<? super T>> {
    private Stack<T> mainStack = new Stack<>();
    private T minimum;

    /**
     * Добавляет элемент на вершину стека
     */
    public void push(T value) {
        mainStack.push(value);
        if (minimum == null) {
            minimum = value;
            return;
        }
        if (minimum.compareTo(value) > 0) {
            minimum = value;
        }
    }

    /**
     * Удаляет и возвращает верхний элемент; если пуст, бросает NoSuchElementException
     */
    public T pop() {
        return mainStack.pop();
    }

    /**
     * Смотрит верхний элемент без удаления; если пуст, бросает NoSuchElementException
     */
    public T peek() {
        return mainStack.peek();
    }

    /**
     * Возвращает минимальный элемент среди всех в стеке без удаления; если пуст, бросает NoSuchElementException
     */
    public T getMin() {
        return minimum;
    }

    /**
     * Возвращает текущий размер стека
     */
    public int size() {
        return mainStack.size();
    }
}
