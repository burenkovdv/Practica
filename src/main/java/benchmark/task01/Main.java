package benchmark.task01;

import lombok.SneakyThrows;

import java.util.*;

public class Main {
    private static final Random RAND = new Random();

    public static void main(String[] args) {
        method(new ArrayList<>());
        method(new LinkedList<>());
        method(new HashSet<>());
        method(new TreeSet<>());
    }

    @SneakyThrows
    public static void method(Collection<Integer> collection) {
        Runnable task = () -> {
            for (int i = 0; i < 1_000_000L; i++) {
                collection.add(i);
            }
        };
        Runnable task2 = () -> {
            if (collection.contains(RAND.nextInt())) {
                System.out.println("FOUND");
            }
        };

        System.out.println(collection.getClass().getSimpleName());
        System.out.println("=".repeat(100));
        benchmark(task);
        benchmark(task2);
        System.out.println("\n");
    }

    public static void benchmark(Runnable task) {
        long totalMemory = Runtime.getRuntime().totalMemory();
        long start = System.currentTimeMillis();
        task.run();
        long duration = System.currentTimeMillis() - start;
        double usedMemory = (totalMemory - Runtime.getRuntime().freeMemory()) / (1024 * 1024.0);
        System.out.println("Duration: " + duration + " ms");
        System.out.printf("Used memory: %.1f mb%n", usedMemory);
    }
}
