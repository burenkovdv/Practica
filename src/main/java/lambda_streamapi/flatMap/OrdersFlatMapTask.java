package lambda_streamapi.flatMap;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.summingDouble;
import static java.util.stream.Collectors.summingInt;

public class OrdersFlatMapTask {

    public static class Item {
        String name;
        int quantity;
        double price;

        public Item(String name, int quantity, double price) {
            this.name = name;
            this.quantity = quantity;
            this.price = price;
        }
    }

    public static class Order {
        String id;
        List<Item> items;

        public Order(String id, List<Item> items) {
            this.id = id;
            this.items = items;
        }
    }

    public static Map<String, Integer> buildProductStatistics(List<Order> orders) {
        return orders.stream()
                .flatMap(x -> x.items.stream())
                .collect(Collectors.groupingBy(x -> x.name, summingInt(item -> item.quantity)));
    }

    public static Map<String, Double> buildRevenueStatistics(List<Order> orders) {
        return orders.stream()
                .flatMap(x -> x.items.stream())
                .collect(Collectors.groupingBy(
                        x -> x.name,
                        LinkedHashMap::new,
                        summingDouble(x -> x.quantity * x.price))
                );

    }

    public static void main(String[] args) {
        List<Order> orders = Arrays.asList(
                new Order("O1", Arrays.asList(new Item("Apple", 3, 10.0), new Item("Banana", 2, 5.0))),
                new Order("O2", Arrays.asList(new Item("Apple", 1, 12.0), new Item("Orange", 5, 8.0))),
                new Order("O3", Arrays.asList(new Item("Banana", 4, 5.0), new Item("Orange", 2, 9.0), new Item("Apple", 2, 11.0)))
        );

        Map<String, Double> revenue = buildRevenueStatistics(orders);
        System.out.println(revenue);
        // Ожидается: {Apple=65.0, Banana=30.0, Orange=58.0}
    }
}
