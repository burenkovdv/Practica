package task06;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<String> input = List.of(
                "Java is great!",
                "I love Java.",
                "Do you love programming?"
        );
        int res = countUniqueWords(input);
        System.out.println(res);
    }

    public static int countUniqueWords(List<String> sentences) {
        Set<String> temp = new HashSet<>();
        for (String sentence : sentences) {
            String[] p = sentence.split("\\s+");
            for (String s : p) {
                temp.add(s.toLowerCase().replaceAll("[.,!?:;]", ""));
            }
        }
        return temp.size();
    }

}
