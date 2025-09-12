package databases.task01;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class TransactionService implements AutoCloseable {

    private Connection connection;

    public TransactionService(Connection connection) {
        this.connection = connection;
    }

    public void transferMoney(int fromId, int toId, BigDecimal amount) throws SQLException
    {
        int fromBalance = getBalance(fromId);

        if (BigDecimal.valueOf(fromBalance).compareTo(amount) < 0) {
            throw new InsufficientFundsException("Account with id = %d has balance %d less than %s".formatted(fromId, fromBalance, amount));
        }

        int toBalance = getBalance(toId);
        String sqlQuery = """
                update accounts set balance=case when id= ? then ?
                when id = ? then ? end
                where id in (?,?);
                """;
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, fromId);
        preparedStatement.setBigDecimal(2, BigDecimal.valueOf(fromBalance).subtract(amount));
        preparedStatement.setInt(3, toId);
        preparedStatement.setBigDecimal(4, BigDecimal.valueOf(toBalance).add(amount));
        preparedStatement.setInt(5,fromId);
        preparedStatement.setInt(6,toId);
        int rowsUpdated = preparedStatement.executeUpdate();
        log.info("Rows updated: {}", rowsUpdated);
    }

    private int getBalance(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select balance from accounts where id = ?");
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("balance");
    }


    @Override
    public void close() throws Exception {
        connection.close();
    }



}
