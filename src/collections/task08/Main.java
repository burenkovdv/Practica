package collections.task08;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> sentences = List.of(
                "Java is great!",
                "I love Java and programming.",
                "Programming in Java is fun."
        );
/*
        List<String> p1 = new ArrayList<>();
        p1.add("java");
        p1.add("is");
        p1.add("great");

        List<String> p2 = new ArrayList<>();
        p2.add("java");
        p2.add("no");
        p2.add("great");

            List<String> p3 = new ArrayList<>(p1);
            p1.removeAll(p2);
            p2.removeAll(p3);
            p1.addAll(p2);

        System.out.println(p1);
*/
        Set<String> res = uniqueWords(sentences);
      //  System.out.println(res);

    }

     public static Set<String> uniqueWords(List<String> sentences){
            Set<String> result = new HashSet<>();
            Set<String> res = new HashSet<>();
            int q=0;
         for (String sentence : sentences) {
             String[] str = sentence.split("\\s+");
             result.clear();
             q++;
             for (String s : str) {
                 result.add(s.toLowerCase().replaceAll("[.,!?:;]",""));
             }

             if(res.size()!=0) {
                 Set<String> temp = new HashSet<>(result);
                 result.removeAll(res);
                 res.removeAll(temp);
                 result.addAll(res);
                 System.out.println(result);
                 continue;
             }
             res.addAll(result);
             //if(q==1) break;
         }
            return result;
     }

}
