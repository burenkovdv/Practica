package optional.task03;

import org.junit.jupiter.api.Test;

import java.util.List;
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

    @Test
    void testMultipleUsersWithCompanies() {
        CompanyFinder.Company c1 = new CompanyFinder.Company(Optional.of("Google"));
        CompanyFinder.Company c2 = new CompanyFinder.Company(Optional.of("Amazon"));
        CompanyFinder.Profile p1 = new CompanyFinder.Profile(c1);
        CompanyFinder.Profile p2 = new CompanyFinder.Profile(c2);
        CompanyFinder.User u1 = new CompanyFinder.User(p1);
        CompanyFinder.User u2 = new CompanyFinder.User(p2);

        List<String> result = CompanyFinder.findAllCompanyNames(List.of(u1, u2));

        assertEquals(List.of("Google", "Amazon"), result);
    }

    @Test
    void testUsersWithMissingCompanies() {
        CompanyFinder.Company c1 = new CompanyFinder.Company(Optional.empty());
        CompanyFinder.Profile p1 = new CompanyFinder.Profile(c1);
        CompanyFinder.User u1 = new CompanyFinder.User(p1);
        CompanyFinder.User u2 = new CompanyFinder.User(null);

        List<String> result = CompanyFinder.findAllCompanyNames(List.of(u1, u2));

        assertTrue(result.isEmpty());
    }

    @Test
    void testMixedValidAndInvalidUsers() {
        CompanyFinder.Company c1 = new CompanyFinder.Company(Optional.of("Tesla"));
        CompanyFinder.Profile p1 = new CompanyFinder.Profile(c1);
        CompanyFinder.User u1 = new CompanyFinder.User(p1);

        CompanyFinder.User u2 = new CompanyFinder.User(null);

        List<String> result = CompanyFinder.findAllCompanyNames(List.of(u1, u2));

        assertEquals(List.of("Tesla"), result);
    }

    @Test
    void testEmptyList() {
        List<String> result = CompanyFinder.findAllCompanyNames(List.of());

        assertTrue(result.isEmpty());
    }
}
