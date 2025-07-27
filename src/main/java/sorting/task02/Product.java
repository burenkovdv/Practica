package sorting.task02;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product implements Comparable<Product> {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public int compareTo(Product o) {
        if (Double.compare(this.getPrice(), o.getPrice()) == 0) {
            return this.getName().compareTo(o.getName());
        }
        return Double.compare(this.getPrice(), o.getPrice());
    }

    @Override
    public String toString() {
        return String.format("%s(%.1f)", name, price);
    }
}
