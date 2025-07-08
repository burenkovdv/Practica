package task07;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<String> sentences = List.of(
                "Java is great!",
                "I think Java is powerful.",
                "Do you also think Java is great?"
        );
        Set<String> res = commonWords(sentences);
        System.out.println(res);
    }

    public static Set<String> commonWords(List<String> sentences){
        Set<String> res = new HashSet<>();
        int q =0;
        for (String sentence : sentences) {
            String[] p = sentence.split("\\s+");
            Set<String> result = new HashSet<>();
            for (String s : p) {
                result.add(s.toLowerCase().replaceAll("[^a-zA-Z]", ""));
            }
            System.out.println(result);
            if(res.size()!=0) {res.retainAll(result); continue;}
            res.addAll(result);
        }
        return res;
    }
}
