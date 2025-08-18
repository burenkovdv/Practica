package lambda_streamapi.task08;

import java.util.*;
import java.util.stream.Collectors;

public class NestedGroupingTask {

    public static class Employee {
        String name;
        String department;
        String position;
        double salary;

        public Employee(String name, String department, String position, double salary) {
            this.name = name;
            this.department = department;
            this.position = position;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return name + " (" + salary + ")";
        }
    }

    public static Map<String, Map<String, List<Employee>>> groupByDeptAndPosition(List<Employee> employees) {
        Map<String, Map<String, List<Employee>>> result = employees.stream()
                .collect(
                        Collectors.groupingBy(x -> x.department,
                                Collectors.groupingBy(y -> y.position,
                                        Collectors.collectingAndThen(
                                                Collectors.toList(),
                                                list -> list.stream()
                                                        .sorted(Comparator.comparingDouble((Employee e) -> e.salary).reversed())
                                                        .toList()
                                        )
                                )
                        )
                );

        return result;
    }

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Андрей", "IT", "Developer", 120000),
                new Employee("Мария", "IT", "Developer", 130000),
                new Employee("Сергей", "IT", "QA", 90000),
                new Employee("Иван", "HR", "Recruiter", 70000),
                new Employee("Елена", "HR", "Recruiter", 75000),
                new Employee("Ольга", "HR", "Manager", 95000),
                new Employee("Павел", "IT", "Manager", 150000)
        );

        Map<String, Map<String, List<Employee>>> result = groupByDeptAndPosition(employees);
        System.out.println(result);
    }
}

