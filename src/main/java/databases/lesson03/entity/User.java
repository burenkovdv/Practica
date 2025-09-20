package databases.lesson03.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class User {
    private Integer id;
    private String name;
    private List<Order> orders;

    public User() {}

    public User(Integer id, String name, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.orders = orders;
    }
}
