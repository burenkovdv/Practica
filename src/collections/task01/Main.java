package task01;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<String> a = List.of("apple", "banana", "pear", "apple");
        List<String> b = List.of("banana", "kiwi", "apple", "banana");
        System.out.println(intersection(a, b));

    }

    public static Set<String> intersection(List<String> list1, List<String> list2) {
        Set<String> res = new HashSet<>();
        Set<String> set1 = new HashSet<>(list1);

        for (String s : list2) {
            if (set1.contains(s)) {
                res.add(s);
            }
        }
        return res;
    }

}


