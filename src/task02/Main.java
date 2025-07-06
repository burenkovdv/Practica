package task02;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<String> a = List.of("apple", "banana", "pear", "apple");
        List<String> b = List.of("banana", "kiwi", "apple");
        System.out.println(difference(a, b)); // ожидается [pear]

    }

    public static Set<String> difference(List<String> list1, List<String> list2) {
        Set<String> res = new HashSet<>(list1);
        res.removeAll(list2);
        return res;
    }
}
