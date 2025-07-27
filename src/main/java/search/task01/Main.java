package search.task01;

import sorting.task02.Product;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Product[] array = {
                new Product("B", 20.0),
                new Product("A", 10.0),
                new Product("C", 30.0)
        };

        Arrays.sort(array);
        int q = Arrays.binarySearch(array,new Product("A", 10.0));
        System.out.println(q);
    }



}
