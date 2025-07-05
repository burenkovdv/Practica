import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<String> input = List.of(
                "Alice:5",
                "Bob:4",
                "Alice:3",
                "Bob:5",
                "Alice:4",
                "Carol:5"
        );
        System.out.println(calculateAverageScores(input));
    }
    public static Map<String, Double> calculateAverageScores(List<String> input) {
        HashMap<String,Double> res = new HashMap<>();
        Map<String,List<Integer>> per = new HashMap<>();
        for (String s : input) {
            String[] st = s.split(":");
            per.computeIfAbsent(st[0], b -> new ArrayList<>()).add(Integer.parseInt(st[1]));
        }

        for (Map.Entry<String,List<Integer>> item : per.entrySet()) {
            List<Integer> grades = item.getValue();
            String student = item.getKey();
            double averageGrade = grades.stream().mapToInt(x -> x).average().getAsDouble();
            res.put(student, averageGrade);
        }

        return res;
    }

}


