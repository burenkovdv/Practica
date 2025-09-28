package databases.lesson04.DAO;

import databases.lesson03.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    Order findById(int id) throws SQLException;

    List<Order> findByUserId(int userId) throws SQLException;

    List<Order> findAll() throws SQLException;

    void save(Order order) throws SQLException;

    void updateAmount(int orderId, int newAmount) throws SQLException;

    void delete(int orderId) throws SQLException;
}
