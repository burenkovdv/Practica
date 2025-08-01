package data_structures.task04;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class HotPotatoGame {
    private final Queue<String> queue;

    /**
     * Создаёт игру с указанным списком участников.
     *
     * @param participants список имён участников; не может быть null или пустым
     * @throws IllegalArgumentException если participants == null || participants.isEmpty()
     */
    public HotPotatoGame(List<String> participants) {
        if (participants == null || participants.isEmpty()) {
            throw new IllegalArgumentException("Participants must not be null or empty");
        }
        queue = new LinkedList<>(participants);
    }

    /**
     * Запускает игру с шагом count.
     *
     * @param count число перепередач до выбывания (count > 0)
     * @return имя последнего оставшегося участника
     * @throws IllegalArgumentException если count <= 0
     */
    public String play(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Некорректный параметр");
        }
        while (queue.size()!=1) {
            for (int i = 0; i < count - 1; i++) {
                String str = queue.poll();
                queue.offer(str);
            }
            queue.poll();

        }

        return queue.peek();
    }
}