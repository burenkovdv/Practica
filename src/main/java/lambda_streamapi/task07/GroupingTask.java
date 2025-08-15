package lambda_streamapi.task07;

import java.util.*;
import java.util.stream.Collectors;

public class GroupingTask {

    public static class Student {
        String name;
        int course;
        double grade;

        public Student(String name, int course, double grade) {
            this.name = name;
            this.course = course;
            this.grade = grade;
        }
    }

    public static Map<Integer, List<String>> groupHighGrades(List<Student> students) {
        Map<Integer, List<String>> result = new HashMap<>();

        result = students.stream()
                .filter(x -> x.grade >= 75)
                .collect(Collectors.groupingBy(
                        student -> student.course,
                        Collectors.mapping(
                                s -> s.name + " (" + s.grade + ")",
                                Collectors.toList()
                        )));
            return result;
    }

    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Андрей", 1, 82.5),
                new Student("Мария", 2, 91.0),
                new Student("Иван", 1, 68.0),
                new Student("Елена", 2, 76.5),
                new Student("Сергей", 3, 72.0)
        );

        Map<Integer, List<String>> result = groupHighGrades(students);
        System.out.println(result);
    }
}

