package regex.task02;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PhoneExtractorTest {

    @Test
    void testPhoneWithPlusFormat() {
        String text = "Call me: +7 999 123-45-67";
        List<String> result = PhoneExtractor.extractPhones(text);
        assertEquals(List.of("+7 999 123-45-67"), result);
    }

    @Test
    void testPhoneWithBrackets() {
        String text = "Office: +7 (999) 123-45-67";
        List<String> result = PhoneExtractor.extractPhones(text);
        assertEquals(List.of("+7 (999) 123-45-67"), result);
    }

    @Test
    void testPhoneWith8Prefix() {
        String text = "Home: 8 999 1234567";
        List<String> result = PhoneExtractor.extractPhones(text);
        assertEquals(List.of("8 999 1234567"), result);
    }

    @Test
    void testPhonePlainDigits() {
        String text = "Number: 89991234567";
        List<String> result = PhoneExtractor.extractPhones(text);
        assertEquals(List.of("89991234567"), result);
    }

    @Test
    void testMultiplePhones() {
        String text = "First: +7 (999) 123-45-67, Second: 89991234567";
        List<String> result = PhoneExtractor.extractPhones(text);
        assertEquals(List.of("+7 (999) 123-45-67", "89991234567"), result);
    }

    @Test
    void testInvalidNumbers() {
        String text = "Wrong: +7 12 345, 123-456, 999999";
        List<String> result = PhoneExtractor.extractPhones(text);
        assertTrue(result.isEmpty());
    }
}
