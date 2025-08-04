package data_structures.priority_queue;

import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        Queue<Task> queue = new PriorityQueue<>();
        queue.add(new Task("task1", 0));
        queue.add(new Task("task2", 3));
        queue.add(new Task("task3", 1));
        queue.add(new Task("task4", 10));

        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }


    }
}

