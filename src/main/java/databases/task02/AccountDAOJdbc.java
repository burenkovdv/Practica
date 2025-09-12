package databases.task02;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AccountDAOJdbc implements AccountDAO {

    private final Connection connection;

    public AccountDAOJdbc() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bdv", "postgres", "12345");
        } catch (SQLException e) {
            log.error("Error during connection.", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findById(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from accounts where id=?;");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return Account.builder()
                .id(id)
                .ownerName(resultSet.getString("owner_name"))
                .balance(resultSet.getBigDecimal("balance"))
                .build();
    }

    @Override
    public List<Account> findAll() throws SQLException {
        List<Account> result = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from accounts");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Account acc = new Account(resultSet.getInt("id"),
                    resultSet.getString("owner_name"),
                    resultSet.getBigDecimal("balance")
            );
            result.add(acc);
        }
        return result;
    }

    @Override
    public void updateBalance(int id, BigDecimal newBalance) throws SQLException {

        String query = """
                update accounts set balance=? where id=?;
                """;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setBigDecimal(1, newBalance);
        preparedStatement.setInt(2, id);
        preparedStatement.executeUpdate();

    }

    @Override
    public void save(Account account) throws SQLException {
        String query =
                "insert into accounts (id,owner_name,balance) values (?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, account.getId());
        preparedStatement.setString(2, account.getOwnerName());
        preparedStatement.setBigDecimal(3, account.getBalance());
        preparedStatement.executeUpdate();

    }

    public void deleteAccount(int id) throws SQLException {
        String query =
            "delete from accounts where id=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();

    }

}
