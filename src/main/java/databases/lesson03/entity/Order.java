package databases.lesson03.entity;

import lombok.Data;

@Data
public class Order {
    private Integer id;
    private Integer userId;
    private String product;
    private Integer amount;

    public Order(Integer id, Integer userId, String product, Integer amount) {
        this.id = id;
        this.userId = userId;
        this.product = product;
        this.amount = amount;
    }
}
