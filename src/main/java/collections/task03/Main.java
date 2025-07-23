package collections.task03;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<String> a = List.of("apple", "banana", "pear", "apple");
        List<String> b = List.of("banana", "kiwi", "apple");

        Set<String> symDiff = symmetricDifference(a, b);
        System.out.println(symDiff);
    }


    public static Set<String> symmetricDifference(List<String> list1, List<String> list2) {
        Set<String> res = new HashSet<>(list1);
        Set<String> res1 = new HashSet<>(list2);
        res.removeAll(list2);
        res1.removeAll(list1);
        res.addAll(res1);
        return res;
    }
}
