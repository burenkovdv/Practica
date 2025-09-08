package optional.task03;

import java.util.List;
import java.util.Optional;

public class CompanyFinder {

    public static Optional<String> findCompanyName(User user) {
        // TODO: реализовать с помощью Optional.ofNullable + map + flatMap

        return Optional.ofNullable(user)
                .map(User::getProfile)
                .map(Profile::getCompany)
                .flatMap(Company::getName);

    }

    public static List<String> findAllCompanyNames(List<User> users) {
        return users.stream()
                .map(user -> Optional.ofNullable(user)
                .map(User::getProfile)
                .map(Profile::getCompany)
                .flatMap(Company::getName))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    // ===================== Models ======================

    public static class User {
        private final Profile profile;

        public User(Profile profile) {
            this.profile = profile;
        }

        public Profile getProfile() {
            return profile;
        }
    }

    public static class Profile {
        private final Company company;

        public Profile(Company company) {
            this.company = company;
        }

        public Company getCompany() {
            return company;
        }
    }

    public static class Company {
        private final Optional<String> name;

        public Company(Optional<String> name) {
            this.name = name;
        }

        public Optional<String> getName() {
            return name;
        }
    }
}
