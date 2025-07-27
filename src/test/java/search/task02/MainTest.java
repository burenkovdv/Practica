package search.task02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class MainTest {

    @Test
    @DisplayName("Возвращает корректный индекс при наличии элемента")
    void testFoundExistingInteger() {
        List<Integer> nums = List.of(1, 3, 5, 7, 9, 11);
        int index = Main.binarySearch(nums, 5);
        assertEquals(2, index, "Элемент 5 должен быть на позиции 2");
    }

    @Test
    @DisplayName("Возвращает отрицательное значение при отсутствии элемента (в середине)")
    void testNotFoundIntegerInMiddle() {
        List<Integer> nums = List.of(1, 3, 5, 7, 9, 11);
        int index = Main.binarySearch(nums, 6);
        assertEquals(-4, index, "6 не найдено, insertionPoint = 3 → −4");
    }

    @Test
    @DisplayName("Возвращает корректный индекс для String")
    void testFoundExistingString() {
        List<String> words = List.of("apple", "banana", "cherry", "date");
        int index = Main.binarySearch(words, "cherry");
        assertEquals(2, index, "«cherry» должен быть на позиции 2");
    }

    @Test
    @DisplayName("Возвращает отрицательное значение для String при отсутствии в конце")
    void testNotFoundStringAfterLast() {
        List<String> words = List.of("apple", "banana", "cherry", "date");
        int index = Main.binarySearch(words, "fig");
        assertEquals(-5, index, "«fig» не найдено, insertionPoint = 4 → −5");
    }

    @Test
    @DisplayName("Пустой список возвращает −1")
    void testEmptyList() {
        List<Integer> empty = List.of();
        int index = Main.binarySearch(empty, 42);
        assertEquals(-1, index, "Пустой список → insertionPoint = 0 → −1");
    }

    @Test
    @DisplayName("Первый и последний элементы")
    void testFirstAndLast() {
        List<Integer> nums = List.of(10, 20, 30, 40);
        assertEquals(0, Main.binarySearch(nums, 10), "Первый элемент → индекс 0");
        assertEquals(3, Main.binarySearch(nums, 40), "Последний элемент → индекс 3");
    }

    @Test
    @DisplayName("Поиск дубликата возвращает любой корректный индекс")
    void testDuplicates() {
        List<Integer> dup = List.of(1, 2, 2, 3);
        int idx = Main.binarySearch(dup, 2);
        assertTrue(idx == 1 || idx == 2, "Для дубликатов 2 допустим индекс 1 или 2");
    }
}