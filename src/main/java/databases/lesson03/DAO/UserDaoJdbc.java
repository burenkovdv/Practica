package databases.lesson03.DAO;

import databases.lesson03.entity.Order;
import databases.lesson03.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserDaoJdbc implements UserDao {

    private final Connection connection;

    public UserDaoJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User findByIdWithOrders(int id) throws SQLException {


        List<Order> list = new ArrayList<>();
        User rezult = new User();

        String query = """
                                SELECT u.id AS user_id, u.name, o.id AS order_id, o.product as product, o.amount as amount
                               FROM users u
                               LEFT JOIN orders o ON u.id = o.user_id
                               WHERE u.id = ?
                               ORDER BY o.id
                """;

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Integer idUser = 0;
        String nameUser = "";
        while (resultSet.next()) {
            Order temp = new Order(
                    resultSet.getInt("order_id"),
                    resultSet.getInt("user_id"),
                    resultSet.getString("product"),
                    resultSet.getInt("amount")
            );

            idUser = temp.getUserId();
            nameUser = resultSet.getString("name");
            if (temp.getId() == 0) {
                break;
            }
            list.add(temp);
        }

        rezult.setId(idUser);
        rezult.setName(nameUser);
        rezult.setOrders(list);
        return rezult;


    }

    @Override
    public List<User> findAllWithOrders() throws SQLException {

        String query = """
                SELECT u.id AS user_id, u.name, o.id AS order_id, o.product as product, o.amount as amount
                FROM users u
                LEFT JOIN orders o ON u.id = o.user_id
                ORDER BY u.id, o.id
                """;

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        Map<Integer, User> userMap = new LinkedHashMap<>();

        while (resultSet.next()) {
            int userId = resultSet.getInt("user_id");
            User user = userMap.computeIfAbsent(userId, id -> {
                User u = new User();
                u.setId(userId);
                try {
                    u.setName(resultSet.getString("name"));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                u.setOrders(new ArrayList<>());
                return u;
            });

            int orderId = resultSet.getInt("order_id");
            if (!resultSet.wasNull()) {
                Order order = new Order(
                        orderId,
                        userId,
                        resultSet.getString("product"),
                        resultSet.getInt("amount")
                );
                user.getOrders().add(order);
            }
        }

        return new ArrayList<>(userMap.values());
    }
}
