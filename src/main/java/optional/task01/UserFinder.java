package optional.task01;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserFinder {

    public static class User {
        private final String name;
        private final int age;
        private final String email;

        public User(String name, int age, String email) {
            this.name = name;
            this.age = age;
            this.email = email;
        }

        public String getName() { return name; }
        public int getAge() { return age; }
        public String getEmail() { return email; }
    }

    /**
     * Найти первого пользователя старше 18 лет, у которого email оканчивается на "@gmail.com".
     * Вернуть Optional<User>.
     */
    public static Optional<User> findAdultGmailUser(List<User> users) {
        return users.stream()
                .filter(x -> x.age > 18)
                .filter(x -> x.email.endsWith("@gmail.com"))
                .findFirst();
    }

    public static String findAdultGmailUserEmail(List<User> users) {
        return findAdultGmailUser(users)
                .map(User::getEmail)
                .orElse("Not found");
    }

    public static List<String> findAllAdultGmailUserEmails(List<User> users) {
        return users.stream().filter(x->x.age>18)
                .filter(x -> x.email.endsWith("@gmail.com"))
                .sorted(Comparator.comparing(User::getName))
                .map(User::getEmail)
                .collect(Collectors.toList());
    }


}

