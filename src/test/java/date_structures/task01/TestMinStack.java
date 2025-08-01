package date_structures.task01;

import data_structures.task01.MinStack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestMinStack {
    @Test
    @DisplayName("Поиск минимального элемента")
    void minElementTest() {
        MinStack<Integer> stack = new MinStack<>();
        stack.push(5);
        stack.push(2);
        stack.push(4);
        int index = stack.getMin();
        assertEquals(2, index, "Минимальное значение 2");
    }

    @Test
    @DisplayName("Поиск минимального значения после Pop")
    void minElementPopTest() {
        MinStack<Integer> stack = new MinStack<>();
        stack.push(5);
        stack.push(2);
        stack.push(4);
        stack.push(1);
        assertEquals(1, stack.getMin());
        stack.pop();
        assertEquals(2, stack.getMin(), "Минимальное значение 2");
        stack.pop();
        stack.pop();
        assertEquals(5, stack.getMin());
    }

    @Test
    @DisplayName("Поиск минимального значения после Peek")
    void minElementPeekTest() {
        MinStack<Integer> stack = new MinStack<>();
        stack.push(5);
        stack.push(1);
        stack.push(4);
        stack.peek();

        int index = stack.getMin();
        assertEquals(1, index, "Минимальное значение 1");

    }


    @Test
    @DisplayName("Проверка пустого стрима Exception")
    void emptyStackTest() {
        MinStack<Integer> stack = new MinStack<>();
        assertThrows(NoSuchElementException.class, stack::getMin);
    }

}
