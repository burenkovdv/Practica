package data_structures.priority_queue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Task и TaskManager Tests")
class TaskManagerTest {

    @Test
    @DisplayName("Task: null name → NPE")
    void taskConstructorNullName() {
        assertThrows(NullPointerException.class, () -> new Task(null, 5));
    }

    @Test
    @DisplayName("Task: compareTo и toString")
    void taskCompareAndToString() {
        Task t1 = new Task("A", 1);
        Task t2 = new Task("B", 2);
        assertTrue(t1.compareTo(t2) < 0);
        assertEquals("Task{name='A', priority=1}", t1.toString());
    }

    @Test
    @DisplayName("TaskManager: конструктор с null и с collection")
    void managerConstructor() {
        TaskManager m1 = new TaskManager(null);
        assertTrue(m1.isEmpty());

        List<Task> init = Arrays.asList(new Task("X", 10), new Task("Y", 5));
        TaskManager m2 = new TaskManager(init);
        assertEquals(2, m2.size());
        assertEquals("Y", m2.peekNext().getName());
    }

    @Test
    @DisplayName("TaskManager.addTask: null → IAE")
    void addTaskNull() {
        TaskManager m = new TaskManager(null);
        assertThrows(IllegalArgumentException.class, () -> m.addTask(null));
    }

    @Test
    @DisplayName("peekNext/pollNext ordering")
    void peekAndPollOrder() {
        TaskManager m = new TaskManager(null);
        m.addTask(new Task("Low", 100));
        m.addTask(new Task("High", 1));
        m.addTask(new Task("Medium", 50));

        assertEquals("High", m.peekNext().getName());
        assertEquals(3, m.size());

        assertEquals("High", m.pollNext().getName());
        assertEquals("Medium", m.pollNext().getName());
        assertEquals("Low", m.pollNext().getName());
        assertTrue(m.isEmpty());
    }

    @Test
    @DisplayName("drainToList возвращает в порядке приоритетов")
    void drainToListTest() {
        List<Task> tasks = Arrays.asList(
                new Task("T1", 3),
                new Task("T2", 1),
                new Task("T3", 2)
        );
        TaskManager m = new TaskManager(tasks);
        List<Task> drained = m.drainToList();
        assertEquals(Arrays.asList(
                new Task("T2", 1),
                new Task("T3", 2),
                new Task("T1", 3)
        ), drained);
        assertTrue(m.isEmpty());
    }

    @Test
    @DisplayName("Constructor: collection с null элементом → IAE")
    void constructorWithNullElement() {
        List<Task> bad = Arrays.asList(new Task("A", 1), null);
        assertThrows(IllegalArgumentException.class, () -> new TaskManager(bad));
    }
}
