package databases.lesson04.DAO;

import databases.lesson03.entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoJdbc implements OrderDao {

    private final Connection connection;

    public OrderDaoJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Order findById(int id) throws SQLException {
        Order rezult = new Order();
        String query = """
                SELECT * FROM ORDERS WHERE ID=?
                """;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            rezult.setId(resultSet.getInt("id"));
            rezult.setUserId(resultSet.getInt("user_id"));
            rezult.setProduct(resultSet.getString("product"));
            rezult.setAmount(resultSet.getInt("amount"));
        }

        return rezult;
    }

    @Override
    public List<Order> findByUserId(int userId) throws SQLException {
        List<Order> rezult = new ArrayList<>();
        String query = """
                SELECT * FROM ORDERS WHERE USER_ID=?
                """;
        PreparedStatement pr = connection.prepareStatement(query);
        pr.setInt(1, userId);
        ResultSet rs = pr.executeQuery();

        while (rs.next()) {
            Order temp = new Order(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getString("product"),
                    rs.getInt("amount")
            );
            rezult.add(temp);
        }
        return rezult;
    }

    @Override
    public List<Order> findAll() throws SQLException {
        List<Order> rezult = new ArrayList<>();
        String query = """
                SELECT * FROM ORDERS
                """;
        PreparedStatement pr = connection.prepareStatement(query);
        ResultSet rs = pr.executeQuery();

        while (rs.next()) {
            Order temp = new Order(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getString("product"),
                    rs.getInt("amount")
            );
            rezult.add(temp);
        }
        return rezult;
    }

    @Override
    public void save(Order order) throws SQLException {
        String query = """
                INSERT INTO ORDERS (ID, USER_ID, PRODUCT,AMOUNT) VALUES (?,?,?,?);
                """;

        PreparedStatement pr = connection.prepareStatement(query);
        pr.setInt(1, order.getId());
        pr.setInt(2, order.getUserId());
        pr.setString(3, order.getProduct());
        pr.setInt(4, order.getAmount());
        pr.executeUpdate();

    }

    @Override
    public void updateAmount(int orderId, int newAmount) throws SQLException {
        String query = """
                UPDATE ORDERS SET AMOUNT=? WHERE ID=?;
                """;
        PreparedStatement pr = connection.prepareStatement(query);
        pr.setInt(1, newAmount);
        pr.setInt(2, orderId);
        pr.executeUpdate();

    }

    @Override
    public void delete(int orderId) throws SQLException {
        String query = """
                DELETE FROM ORDERS WHERE ID=?;
                """;
        PreparedStatement pr = connection.prepareStatement(query);
        pr.setInt(1, orderId);
        pr.executeUpdate();

    }
}
