package date_structures.task02;

import data_structures.task02.StackWithMax;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestStackWithMax {
    @Test
    @DisplayName("Проверка метода size")
    void testSize() {
        StackWithMax<Integer> element = new StackWithMax<>();
        int index = element.size();
        assertEquals(0, index, "Результат 0");
    }
}
