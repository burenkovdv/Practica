package lambda_streamapi.homework_1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private Main.Employee emp(double salary, String name) {
        return new Main.Employee(UUID.randomUUID(), "TestDept", name, salary);
    }

    private Map<String, List<Main.Employee>> inputMap;

    @BeforeEach
    void setup() {
        inputMap = new HashMap<>();
        inputMap.put("Dept1", List.of(
                emp(60_000, "Alice"),
                emp(200_000, "Bob"),
                emp(350_000, "Charlie")
        ));
        inputMap.put("Dept2", List.of(
                emp(120_000, "Diana"),
                emp(280_000, "Eve"),
                emp(500_000, "Frank")
        ));
    }

    @Test
    @DisplayName("Карта содержит все ожидаемые ключи (50k-150k, 150k-300k, 300k+)")
    void testGroupingContainsExpectedKeys() {
        Map<String, List<Main.Employee>> result = Main.rearrange(inputMap);
        assertTrue(result.containsKey("50k-150k"));
        assertTrue(result.containsKey("150k-300k"));
        assertTrue(result.containsKey("300k+"));
    }

    @Test
    @DisplayName("Сотрудники распределены в правильные диапазоны зарплат")
    void testEmployeesInCorrectRanges() {
        Map<String, List<Main.Employee>> result = Main.rearrange(inputMap);
        assertEquals(2, result.get("50k-150k").size());
        assertEquals(2, result.get("150k-300k").size());
        assertEquals(2, result.get("300k+").size());
    }

    @Test
    @DisplayName("Сортировка по зарплате внутри диапазона по убыванию")
    void testSortingWithinRange() {
        Map<String, List<Main.Employee>> result = Main.rearrange(inputMap);
        List<Main.Employee> midRange = result.get("150k-300k");
        assertTrue(midRange.get(0).salary() >= midRange.get(1).salary(),
                "Employees should be sorted by descending salary");
    }

    @Test
    @DisplayName("Пустая входная карта возвращает пустую карту")
    void testEmptyInputReturnsEmptyMap() {
        Map<String, List<Main.Employee>> result = Main.rearrange(Collections.emptyMap());
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Все сотрудники с низкой зарплатой попадают только в диапазон 50k-150k")
    void testSingleDepartmentAllLowSalary() {
        Map<String, List<Main.Employee>> input = Map.of("DeptX", List.of(
                emp(55_000, "A"), emp(75_000, "B"), emp(149_999, "C")
        ));
        Map<String, List<Main.Employee>> result = Main.rearrange(input);
        assertEquals(3, result.get("50k-150k").size());
        assertTrue(result.get("150k-300k").isEmpty());
        assertTrue(result.get("300k+").isEmpty());
    }

    @Test
    @DisplayName("Все сотрудники с высокой зарплатой попадают только в диапазон 300k+")
    void testSingleDepartmentAllHighSalary() {
        Map<String, List<Main.Employee>> input = Map.of("DeptX", List.of(
                emp(310_000, "A"), emp(400_000, "B")
        ));
        Map<String, List<Main.Employee>> result = Main.rearrange(input);
        assertEquals(2, result.get("300k+").size());
        assertTrue(result.get("50k-150k").isEmpty());
        assertTrue(result.get("150k-300k").isEmpty());
    }

    @Test
    @DisplayName("Граничные значения зарплаты (150k и 300k) попадают в правильные диапазоны")
    void testSalaryExactlyOnBoundary() {
        Map<String, List<Main.Employee>> input = Map.of("DeptX", List.of(
                emp(150_000, "A"),
                emp(300_000, "B")
        ));
        Map<String, List<Main.Employee>> result = Main.rearrange(input);
        assertEquals(1, result.get("150k-300k").size());
        assertEquals(1, result.get("300k+").size());
    }

    @Test
    @DisplayName("Имена сотрудников сохраняются после перегруппировки")
    void testNamesPreservedInResult() {
        Map<String, List<Main.Employee>> result = Main.rearrange(inputMap);
        boolean containsAlice = result.values().stream()
                .flatMap(List::stream)
                .anyMatch(e -> e.name().equals("Alice"));
        assertTrue(containsAlice);
    }

    @Test
    @DisplayName("Сортировка по убыванию зарплаты выполняется внутри каждой группы")
    void testSortingAcrossGroupsNotRequiredButWithinEachGroup() {
        Map<String, List<Main.Employee>> result = Main.rearrange(inputMap);
        for (List<Main.Employee> group : result.values()) {
            for (int i = 0; i < group.size() - 1; i++) {
                assertTrue(group.get(i).salary() >= group.get(i + 1).salary(),
                        "Group should be sorted by salary descending");
            }
        }
    }

    @Test
    @DisplayName("Количество сотрудников до и после перегруппировки одинаково (нет потерь)")
    void testNoLossOfEmployees() {
        int originalCount = inputMap.values().stream().mapToInt(List::size).sum();
        Map<String, List<Main.Employee>> result = Main.rearrange(inputMap);
        int rearrangedCount = result.values().stream().mapToInt(List::size).sum();
        assertEquals(originalCount, rearrangedCount);
    }
}