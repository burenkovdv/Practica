package databases.lesson03.DAO;

import databases.lesson03.entity.Order;
import databases.lesson03.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        List<Order> list = new ArrayList<>();
        List<User> rezult = new ArrayList<>();

        String query = """
                SELECT u.id AS user_id, u.name, o.id AS order_id, o.product as product, o.amount as amount
                FROM users u
                LEFT JOIN orders o ON u.id = o.user_id
                """;

        PreparedStatement preparedStatement = connection.prepareStatement(query);
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
            if (idUser != temp.getUserId() && idUser != 0) {
                User user = new User(idUser, nameUser, new ArrayList<>(list));
                rezult.add(user);
                list.clear();
            }
            list.add(temp);
            idUser = temp.getUserId();
            nameUser = resultSet.getString("name");

            if (resultSet.isLast()) {
                User user = new User(idUser, nameUser, new ArrayList<>(list));
                rezult.add(user);
            }
        }
        return rezult;
    }
}
