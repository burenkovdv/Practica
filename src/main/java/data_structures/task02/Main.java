package data_structures.task02;

public class Main {
    public static void main(String[] args) {
        StackWithMax<Integer> stack = new StackWithMax<>();

        stack.push(3);
        stack.push(5);
        System.out.println(stack.getMax()); // → 5

        stack.push(2);
        stack.push(1);
        System.out.println(stack.getMax()); // → 5

        stack.pop();
        stack.pop();
        System.out.println(stack.getMax()); // → 5

        stack.push(10);
        System.out.println(stack.getMax()); // → 10
    }

}
