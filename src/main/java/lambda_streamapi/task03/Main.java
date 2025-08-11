package lambda_streamapi.task03;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {
    private static List<String> initialList = List.of("java", "python", "c++", "go", "javascript", "kotlin");

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(initialList);
        list.removeIf(x -> x.length() < 4);
        System.out.println(list);

        list = new ArrayList<>(initialList);
        list.replaceAll(String::toUpperCase);
        System.out.println(list);

        list = new ArrayList<>(initialList);
        list.sort((s1, s2) -> s2.compareTo(s1));
        System.out.println(list);

        list = new ArrayList<>(initialList);
        list.forEach(System.out::println);
    }
}
