package data_structures;

import data_structures.task03.BoundedQueue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты для BoundedQueue")
class BoundedQueueTest {

    @Test
    @DisplayName("Конструктор: отрицательная или нулевая ёмкость")
    void constructorShouldThrowForNonPositiveCapacity() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new BoundedQueue<>(0)),
                () -> assertThrows(IllegalArgumentException.class, () -> new BoundedQueue<>(-5))
        );
    }

    @Test
    @DisplayName("isEmpty/isFull и size на пустой очереди")
    void emptyQueueProperties() {
        BoundedQueue<String> q = new BoundedQueue<>(3);
        assertTrue(q.isEmpty());
        assertFalse(q.isFull());
        assertEquals(0, q.size());
        assertThrows(NoSuchElementException.class, q::dequeue);
        assertThrows(NoSuchElementException.class, q::peek);
    }

    @Test
    @DisplayName("enqueue/dequeue одного элемента")
    void enqueueDequeueSingleElement() {
        BoundedQueue<Integer> q = new BoundedQueue<>(2);
        q.enqueue(42);
        assertFalse(q.isEmpty());
        assertEquals(1, q.size());
        assertEquals(42, q.peek());
        assertEquals(42, q.dequeue());
        assertTrue(q.isEmpty());
    }

    @Test
    @DisplayName("Заполнение и переполнение очереди")
    void fillingAndOverflow() {
        BoundedQueue<String> q = new BoundedQueue<>(2);
        q.enqueue("A");
        q.enqueue("B");
        assertTrue(q.isFull());
        assertThrows(IllegalStateException.class, () -> q.enqueue("C"));
    }

    @Test
    @DisplayName("Чередование enqueue/dequeue (кольцевой буфер)")
    void circularBehavior() {
        BoundedQueue<Integer> q = new BoundedQueue<>(3);
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        assertEquals(1, q.dequeue());
        q.enqueue(4);
        // Теперь в очереди должны быть 2,3,4
        assertEquals(2, q.dequeue());
        assertEquals(3, q.dequeue());
        assertEquals(4, q.dequeue());
        assertTrue(q.isEmpty());
    }

    @Test
    @DisplayName("peek не удаляет элемент")
    void peekDoesNotRemove() {
        BoundedQueue<String> q = new BoundedQueue<>(1);
        q.enqueue("X");
        assertEquals("X", q.peek());
        assertEquals(1, q.size());
    }
}