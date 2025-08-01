package data_structures.task01;

import java.util.NoSuchElementException;
import java.util.Stack;

public class MinStack<T extends Comparable<? super T>> {
    private Stack<T> mainStack = new Stack<>();
    private Stack<T> minStack = new Stack<>();
    //private T minimum;

    /**
     * Добавляет элемент на вершину стека
     */
    public void push(T value) {
        mainStack.push(value);
        if (minStack.isEmpty() || value.compareTo(minStack.peek()) <= 0) {
            minStack.push(value);
            return;
        }
        minStack.push(minStack.peek());
    }

    /**
     * Удаляет и возвращает верхний элемент; если пуст, бросает NoSuchElementException
     */
    public T pop() {
        minStack.pop();
        return mainStack.pop();
    }

    /**
     * Смотрит верхний элемент без удаления; если пуст, бросает NoSuchElementException
     */
    public T peek() {
        checkStack();
        return mainStack.peek();
    }

    /**
     * Возвращает минимальный элемент среди всех в стеке без удаления; если пуст, бросает NoSuchElementException
     */
    public T getMin() {
        checkStack();
        return minStack.peek();
    }

    /**
     * Возвращает текущий размер стека
     */
    public int size() {
        return mainStack.size();
    }

    void checkStack() {
        if (mainStack.isEmpty()) {
            throw new NoSuchElementException();
        }
    }


}
