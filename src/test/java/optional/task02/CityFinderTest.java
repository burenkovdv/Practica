package optional.task02;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CityFinderTest {

    @Test
    void testUserWithCity() {
        CityFinder.Address address = new CityFinder.Address("Moscow");
        CityFinder.User user = new CityFinder.User("Ivan", address);

        Optional<String> result = CityFinder.findUserCity(user);

        assertEquals(Optional.of("Moscow"), result);
    }

    @Test
    void testUserWithoutAddress() {
        CityFinder.User user = new CityFinder.User("Ivan", null);

        Optional<String> result = CityFinder.findUserCity(user);

        assertTrue(result.isEmpty());
    }

    @Test
    void testNullUser() {
        Optional<String> result = CityFinder.findUserCity(null);

        assertTrue(result.isEmpty());
    }

    @Test
    void testAddressWithoutCity() {
        CityFinder.Address address = new CityFinder.Address(null);
        CityFinder.User user = new CityFinder.User("Ivan", address);

        Optional<String> result = CityFinder.findUserCity(user);
        assertTrue(result.isEmpty());
    }

    @Test
    void testUserWithCity1() {
        CityFinder.Address address = new CityFinder.Address("Moscow");
        CityFinder.User user = new CityFinder.User("Ivan", address);

        String result = CityFinder.findUserCityOrDefault(user, "Unknown");

        assertEquals("Moscow", result);
    }

    @Test
    void testUserWithoutAddress1() {
        CityFinder.User user = new CityFinder.User("Ivan", null);

        String result = CityFinder.findUserCityOrDefault(user, "Unknown");

        assertEquals("Unknown", result);
    }

    @Test
    void testNullUser1() {
        String result = CityFinder.findUserCityOrDefault(null, "Unknown");

        assertEquals("Unknown", result);
    }

    @Test
    void testAddressWithoutCity1() {
        CityFinder.Address address = new CityFinder.Address(null);
        CityFinder.User user = new CityFinder.User("Ivan", address);

        String result = CityFinder.findUserCityOrDefault(user, "Unknown");

        assertEquals("Unknown", result);
    }

    @Test
    void testCustomDefaultCity() {
        CityFinder.User user = null;

        String result = CityFinder.findUserCityOrDefault(user, "DefaultCity");

        assertEquals("DefaultCity", result);
    }

}
