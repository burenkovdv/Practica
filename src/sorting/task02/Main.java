package sorting.task02;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Product[] products = {
                new Product("Bread", 2.5),
                new Product("Milk", 1.8),
                new Product("Eggs", 2.5),
                new Product("Butter", 3.0)
        };

        Arrays.sort(products);
        System.out.println(Arrays.toString(products));
        // → [Milk(1.8), Bread(2.5), Eggs(2.5), Butter(3.0)]
    }

}
