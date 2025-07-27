package sorting.task01;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AgeComparatorTest {

    @Test
    void testComparator_success() {
       var people = new ArrayList<>(List.of(
                new Person("Alice", 30),
                new Person("Bob",   25),
                new Person("Alice", 25),
                new Person("Charlie", 35)
        ));
       people.sort(new AgeComparator());

       assertEquals("Bob", people.getFirst().getName());
       assertEquals("Alice", people.get(1).getName());
    }
}