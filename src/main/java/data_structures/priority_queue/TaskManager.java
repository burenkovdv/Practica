package data_structures.priority_queue;

import java.util.*;

public class TaskManager {
    private final PriorityQueue<Task> queue;

    /**
     * Создаёт менеджер задач.
     * <p>
     * //   * @param initialTasks опциональный список начальных задач; если null — создаётся пустой менеджер
     */
    public TaskManager(Collection<Task> initialTasks) {
        if (initialTasks != null) {
            if (initialTasks.contains(null)) {
                throw new IllegalArgumentException("Найден элемент null");
            }
            this.queue = new PriorityQueue<>(initialTasks);
            return;
        }
        this.queue = new PriorityQueue<>();
    }


    /**
     * Добавляет задачу в менеджер.
     *
     * @param task не может быть null
     * @throws IllegalArgumentException если task == null
     */
    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Параметр не может быть null");
        }
        queue.add(task);
    }

    /**
     * Возвращает, но не удаляет задачу с наивысшим приоритетом.
     *
     * @return задача или null, если пусто
     */
    public Task peekNext() {
        return queue.peek();
    }

    /**
     * Удаляет и возвращает задачу с наивысшим приоритетом.
     *
     * @return задача или null, если пусто
     */
    public Task pollNext() {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.remove();
    }

    /**
     * @return количество задач в менеджере
     */
    public int size() {
        return queue.size();
    }

    /**
     * @return true, если нет ни одной задачи
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public List<Task> drainToList() {
        ArrayList<Task> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            Task task = queue.poll();
            list.add(task);
        }
        return list;
    }



    @Override
    public String toString() {
        return "TaskManager{" +
                "queue=" + queue +
                '}';
    }



}

