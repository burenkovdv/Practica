package sorting.task01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>(List.of(
                new Person("Alice", 30),
                new Person("Bob",   25),
                new Person("Alice", 25),
                new Person("Charlie", 35)
        ));

        Comparator<Person> ageComparator = new AgeComparator().reversed();
        Comparator<Person> nameComparator = new NameComparator();
        Comparator<Person> nameAgeComparator = nameComparator.thenComparing(ageComparator);
        // 1) Сортировка по возрасту
        Collections.sort(people, ageComparator);
        System.out.println(people);
        // → [Bob(25), Alice(25), Alice(30), Charlie(35)]

        // 2) Сортировка по имени, при равных именах — по убыванию возраста
        Collections.sort(people, nameAgeComparator);
        System.out.println(people);
        // → [Alice(30), Alice(25), Bob(25), Charlie(35)]
    }

}
