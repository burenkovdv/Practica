package optional.task03;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CompanyFinderTest {

    @Test
    void testUserWithCompanyName() {
        CompanyFinder.Company company = new CompanyFinder.Company(Optional.of("Google"));
        CompanyFinder.Profile profile = new CompanyFinder.Profile(company);
        CompanyFinder.User user = new CompanyFinder.User(profile);

        Optional<String> result = CompanyFinder.findCompanyName(user);

        assertEquals(Optional.of("Google"), result);
    }

    @Test
    void testUserWithoutProfile() {
        CompanyFinder.User user = new CompanyFinder.User(null);

        Optional<String> result = CompanyFinder.findCompanyName(user);

        assertTrue(result.isEmpty());
    }

    @Test
    void testProfileWithoutCompany() {
        CompanyFinder.Profile profile = new CompanyFinder.Profile(null);
        CompanyFinder.User user = new CompanyFinder.User(profile);

        Optional<String> result = CompanyFinder.findCompanyName(user);

        assertTrue(result.isEmpty());
    }

    @Test
    void testCompanyWithoutName() {
        CompanyFinder.Company company = new CompanyFinder.Company(Optional.empty());
        CompanyFinder.Profile profile = new CompanyFinder.Profile(company);
        CompanyFinder.User user = new CompanyFinder.User(profile);

        Optional<String> result = CompanyFinder.findCompanyName(user);

        assertTrue(result.isEmpty());
    }

    @Test
    void testNullUser() {
        Optional<String> result = CompanyFinder.findCompanyName(null);

        assertTrue(result.isEmpty());
    }
}
