package collections.task05;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<String> sentences = List.of(
                "Hello, world!",
                "Java is great.",
                "Hello again: Java is powerful."
        );
        Set<String> res = worldDistinct(sentences);
        System.out.println(res);
    }
    public static Set<String> worldDistinct (List<String> world) {
        Set<String> result = new HashSet<>();
        List<String> temp = new ArrayList<>();
        for (String item : world) {
            String[] text = item.split(" ");
            for (String s : text) {
                temp.add(s.toLowerCase().replaceAll("[,.!:;?]",""));
            }
        }

        for (String s : temp) {
            result.add(s);
        }

        return result;
    }


}
