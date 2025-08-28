package regex.task01;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailExtractorTest {

    @Test
    void testSingleEmail() {
        String text = "Contact me at user@example.com";
        List<String> result = EmailExtractor.extractEmails(text);
        assertEquals(List.of("user@example.com"), result);
    }

    @Test
    void testMultipleEmails() {
        String text = "First: a@b.com, Second: xyz-123@mail.ru";
        List<String> result = EmailExtractor.extractEmails(text);
        assertEquals(List.of("a@b.com", "xyz-123@mail.ru"), result);
    }

    @Test
    void testInvalidEmails() {
        String text = "Bad: user@@domain.com test@ test@domain .com";
        List<String> result = EmailExtractor.extractEmails(text);
        assertTrue(result.isEmpty());
    }

    @Test
    void testMixedValidAndInvalid() {
        String text = "Valid: ok@mail.com Invalid: bad@, also@wrong";
        List<String> result = EmailExtractor.extractEmails(text);
        assertEquals(List.of("ok@mail.com"), result);
    }

    @Test
    void testEmailWithSubdomain() {
        String text = "Support: help@service.company.org";
        List<String> result = EmailExtractor.extractEmails(text);
        assertEquals(List.of("help@service.company.org"), result);
    }

    @Test
    void testEmailWithLongTld() {
        String text = "My new domain: contact@mywebsite.online";
        List<String> result = EmailExtractor.extractEmails(text);
        // Ожидается корректный захват с длинным TLD (.online)
        assertEquals(List.of("contact@mywebsite.online"), result);
    }

    @Test
    void testEmailWithSubdomains() {
        String text = "Support: support@it.department.company.org";
        List<String> result = EmailExtractor.extractEmails(text);
        // Ожидается полный адрес, включая subdomain
        assertEquals(List.of("support@it.department.company.org"), result);
    }

    @Test
    void testMultipleDifferentTlds() {
        String text = "First: user1@site.info Second: user2@domain.museum Third: user3@data.io";
        List<String> result = EmailExtractor.extractEmails(text);
        assertEquals(
                List.of("user1@site.info", "user2@domain.museum", "user3@data.io"),
                result
        );
    }

    @Test
    void testInvalidDoubleAt() {
        String text = "Wrong: hello@@domain.com";
        List<String> result = EmailExtractor.extractEmails(text);
        // Не должен находить email
        assertTrue(result.isEmpty());
    }

    @Test
    void testEmailWithDashAndUnderscore() {
        String text = "Login: user_name-123@my-domain.org";
        List<String> result = EmailExtractor.extractEmails(text);
        assertEquals(List.of("user_name-123@my-domain.org"), result);
    }
}