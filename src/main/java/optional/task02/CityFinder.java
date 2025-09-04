package optional.task02;

import lombok.Data;

import java.util.Optional;

public class CityFinder {

    @Data
    static class Address {
        private final String city;
    }

    @Data
    static class User {
        private final String name;
        private final Address address;
    }

    public static Optional<String> findUserCity(User user) {
        return Optional.ofNullable(user)
                .map(User::getAddress)
                .map(Address::getCity);
    }

    public static String findUserCityOrDefault(User user, String defaultCity) {
        return Optional.ofNullable(user)
                .map(User::getAddress)
                .map(Address::getCity)
                .orElse(defaultCity);
    }

}
