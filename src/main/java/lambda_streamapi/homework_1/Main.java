package lambda_streamapi.homework_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.ToLongFunction;

public class Main {
    public record Employee(UUID personId, String department, String name, double salary) {
    }

    /**
     * TODO: с помощью стрима нужно:
     *  1. вывести размер каждого списка сотрудников в мапе для каждого ключа-департамента (peek)
     *  2. разгладить структуру в стрим сотрудников (flatMap)
     *  3. отсортировать по убыванию зарплаты
     *  4. сгруппировать по диапазонам зарплат в Map<String, List<Employee>> (диапазоны: "50k-150k", "150k-300k", "300k+")
     *  Должно получиться так:
     *  {"50k-150k" : [Employee1, Employee2, Employee3, ...], "150k-300k" : [Employee4, Employee5, ...], ...}
     *  Бонусное задание:
     *  5. Сделать так, чтобы в результате была не Map<String, List<Employee>>, а Map<String, List<String>> с именами в списке вместо объектов
     */
    public static Map<String, List<Employee>> rearrange(Map<String, List<Employee>> employeeMap) {
        throw new UnsupportedOperationException();
    }

    /**
     * Возвращает размер объекта в байтах
     *
     * @param object       исходный объект
     * @param sizeFunction функция, которая определяет размер
     * @param <T>          тип объекта
     * @return количество байтов
     */
    public static <T> long calculateSize(T object, ToLongFunction<T> sizeFunction) {
        return sizeFunction.applyAsLong(object);
    }

    public static Map<String, List<Employee>> generateEmployeeMap() {
        List<String> departments = List.of("Engineering", "HR", "Finance", "Marketing");
        Map<String, List<Employee>> departmentEmployees = new HashMap<>();
        for (String department : departments) {
            int numberOfEmployees = ThreadLocalRandom.current().nextInt(3, 16); // 3-15 employees
            List<Employee> employees = new ArrayList<>();
            for (int i = 1; i <= numberOfEmployees; i++) {
                UUID id = UUID.randomUUID();
                String name = generateRandomName();
                double salary = ThreadLocalRandom.current().nextDouble(50_000, 500_000);
                employees.add(new Employee(id, department, name, salary));
            }
            departmentEmployees.put(department, employees);
        }
        return departmentEmployees;
    }

    private static String generateRandomName() {
        String[] firstNames = {"Alice", "Bob", "Charlie", "Diana", "Eve", "Frank", "Grace", "Helen", "Ian", "Jane"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis"};
        String first = firstNames[ThreadLocalRandom.current().nextInt(firstNames.length)];
        String last = lastNames[ThreadLocalRandom.current().nextInt(lastNames.length)];
        return first + " " + last;
    }
}