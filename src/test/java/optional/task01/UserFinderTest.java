package optional.task01;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserFinderTest {

    @Test
    void testFindAdultGmailUserExists() {
        List<UserFinder.User> users = List.of(
                new UserFinder.User("Ivan", 16, "ivan@yandex.ru"),
                new UserFinder.User("Maria", 20, "maria@gmail.com"),
                new UserFinder.User("Oleg", 25, "oleg@mail.ru")
        );

        Optional<UserFinder.User> result = UserFinder.findAdultGmailUser(users);

        assertTrue(result.isPresent());
        assertEquals("Maria", result.get().getName());
    }

    @Test
    void testNoAdultGmailUser() {
        List<UserFinder.User> users = List.of(
                new UserFinder.User("Ivan", 16, "ivan@gmail.com"),
                new UserFinder.User("Oleg", 25, "oleg@mail.ru")
        );

        Optional<UserFinder.User> result = UserFinder.findAdultGmailUser(users);

        assertTrue(result.isEmpty());
    }

    @Test
    void testMultipleAdultGmailUsers() {
        List<UserFinder.User> users = List.of(
                new UserFinder.User("Maria", 22, "maria@gmail.com"),
                new UserFinder.User("Oleg", 25, "oleg@gmail.com")
        );

        Optional<UserFinder.User> result = UserFinder.findAdultGmailUser(users);

        assertTrue(result.isPresent());
        assertEquals("Maria", result.get().getName(), "Должен вернуться первый найденный");
    }

    @Test
    void testEmptyList() {
        List<UserFinder.User> users = List.of();

        Optional<UserFinder.User> result = UserFinder.findAdultGmailUser(users);

        assertTrue(result.isEmpty());
    }

    @Test
    void testNoAdultGmailUsers() {
        List<UserFinder.User> users = List.of(
                new UserFinder.User("Ivan", 17, "ivan@gmail.com"),
                new UserFinder.User("Sergey", 20, "sergey@mail.ru")
        );

        List<String> result = UserFinder.findAllAdultGmailUserEmails(users);

        assertTrue(result.isEmpty());
    }

    @Test
    void testSortingByName() {
        List<UserFinder.User> users = List.of(
                new UserFinder.User("Zoya", 21, "zoya@gmail.com"),
                new UserFinder.User("Anna", 22, "anna@gmail.com"),
                new UserFinder.User("Pavel", 25, "pavel@gmail.com")
        );

        List<String> result = UserFinder.findAllAdultGmailUserEmails(users);

        assertEquals(List.of("anna@gmail.com", "pavel@gmail.com", "zoya@gmail.com"), result);
    }
}
