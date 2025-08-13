package lambda_streamapi.task05;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    static Random RAND = new Random();

    public static void main(String[] args) throws InterruptedException {

        List<String> strings = new LinkedList<>();

        for (int i = 0; i < 1_000_000; i++) {
            int length = RAND.nextInt(1, 21);
            strings.add(generateString(length));
        }
        List<String> list = strings.stream()
                .filter(x -> x.length() == 3)
                .map(x->x.toUpperCase())
                .sorted(Comparator.reverseOrder())
                .toList();
        System.out.println(list);

        final Map<Integer, List<String>> collect = strings.stream()
                .collect(Collectors.groupingBy(x -> x.length()));
    }

    public static String generateString(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive");
        }
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RAND.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }
}