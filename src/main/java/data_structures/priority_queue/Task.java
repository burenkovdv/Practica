package data_structures.priority_queue;

import java.util.Objects;

public class Task implements Comparable<Task> {
    private String name;
    private int priority;

    public Task(String name, int priority) {
        if (name.isEmpty()) {
            throw new NullPointerException("Параметр не задан");
        }
        this.name = name;
        this.priority = priority;
    }

    public Task(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Параметр не задан");
        }
        this.name = name;
    }

    @Override
    public int compareTo(Task o) {
        return Integer.compare(this.priority, o.priority);
    }

    public String getName() {
        return name;
    }


    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;
        return priority == task.priority && Objects.equals(name, task.name);
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", priority=" + priority +
                '}';
    }
}