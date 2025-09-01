package regex.task03;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PhoneFormatterTest {

    @Test
    void testInvalidIgnored() {
        String text = "Неверные: +7 99 123-45-67, 7 999 123-45-67, +1 999 123-45-67, +700000000000, +7(999)123-45-6";
        List<String> result = PhoneFormatter.extractAndFormatPhones(text);
        assertTrue(result.isEmpty(), "Все номера должны быть отбракованы как невалидные");
    }

    @Test
    void testNoPhones() {
        String text = "Тут вообще нет телефонов, только текст и знаки препинания!";
        List<String> result = PhoneFormatter.extractAndFormatPhones(text);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSimpleFormatting() {
        String text = "Call me: 89991234567 or +7(999)1234567";
        List<String> result = PhoneFormatter.extractAndFormatPhones(text);
        assertEquals(List.of("+7 (999) 123-45-67"), result);
    }

    @Test
    void testMultipleDifferentNumbers() {
        String text = "First: 8-921-555-6677, Second: +7 495 0001122, Third: +7-812-123-45-67";
        List<String> result = PhoneFormatter.extractAndFormatPhones(text);
        assertEquals(List.of(
                "+7 (921) 555-66-77",
                "+7 (495) 000-11-22",
                "+7 (812) 123-45-67"
        ), result);
    }

    @Test
    void testDuplicatesAreRemoved() {
        String text = "Повтор: +7 (921) 5556677 и 89215556677";
        List<String> result = PhoneFormatter.extractAndFormatPhones(text);
        assertEquals(List.of("+7 (921) 555-66-77"), result);
    }

    @Test
    void testInvalidNumbersIgnored() {
        String text = "Ошибки: 12345, +7-999-12-34, +700000000000";
        List<String> result = PhoneFormatter.extractAndFormatPhones(text);
        assertTrue(result.isEmpty(), "Ни один номер не должен пройти валидацию");
    }

    @Test
    void testPreservesOrder() {
        String text = "Сначала: 89991112233, потом: +7 (812) 111-22-33";
        List<String> result = PhoneFormatter.extractAndFormatPhones(text);
        assertEquals(List.of(
                "+7 (999) 111-22-33",
                "+7 (812) 111-22-33"
        ), result);
    }
}
