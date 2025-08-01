package data_structures.task02;

import java.util.Stack;

public class StackWithMax<T extends Comparable<? super T>> {
    private Stack<T> mainStack = new Stack<>();
    private Stack<T> maxStack = new Stack<>();


    void push(T value) {
        mainStack.push(value);
        if(maxStack.isEmpty() || value.compareTo(maxStack.peek())>0){
            maxStack.push(value);
            return;
        }
        maxStack.push(maxStack.peek());
    }

    public T pop() {
        maxStack.pop();
        return mainStack.pop();
    }

    public T peek() {
        return mainStack.peek();
    }

    public T getMax(){
        return maxStack.peek();
    }
    public boolean isEmpty () {
        return mainStack.isEmpty();
    }

    public int size() {
        return mainStack.size();
    }


}
