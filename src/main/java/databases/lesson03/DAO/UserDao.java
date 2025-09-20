package databases.lesson03.DAO;

import databases.lesson03.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    User findByIdWithOrders(int id) throws SQLException;

    List<User> findAllWithOrders() throws SQLException;
}
