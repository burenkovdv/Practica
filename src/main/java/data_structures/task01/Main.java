package data_structures.task01;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        MinStack<Integer> stack = new MinStack<>();

        stack.push(5);
        stack.push(2);
        stack.push(4);
        System.out.println(stack.getMin()); // → 2

        stack.push(1);
        System.out.println(stack.getMin()); // → 1

        System.out.println(stack.pop());    // → 1
        System.out.println(stack.getMin()); // → 2

        System.out.println(stack.peek());   // → 4
        System.out.println(stack.size());   // → 3

        stack.pop(); // 4
        stack.pop(); // 2
        System.out.println(stack.getMin()); // → 5
    }

}
