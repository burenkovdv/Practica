package databases.task01;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class Main {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bdv", "postgres", "12345");
        try (TransactionService transactionService = new TransactionService(connection)) {
            transactionService.transferMoney(1, 2, BigDecimal.valueOf(1000));
        } catch (Exception exception) {
            log.error("Ошибка при транзакции", exception);
        }
    }
}
