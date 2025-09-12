package databases.task02;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountDAOJdbcTest {

    private AccountDAOJdbc accountDAO = new AccountDAOJdbc();
    private static Connection connection;
    private static Statement statement;

    @BeforeAll
    static void beforeAll() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bdv", "postgres", "12345");
        statement = connection.createStatement();
    }

    @AfterAll
    static void afterAll() throws SQLException {
        statement.close();
        connection.close();
    }

    @BeforeEach
    void setUp() throws SQLException {
        statement.execute("TRUNCATE TABLE accounts RESTART IDENTITY");
        generateTestData();
    }

    private void generateTestData() throws SQLException {
        String query = """
                INSERT INTO accounts (owner_name, balance) VALUES
                ('Андрей', 1200.50),
                ('Мария', 850.00),
                ('Сергей', 300.75),
                ('Ольга', 5000.00),
                ('Иван', 150.25),
                ('Елена', 2200.00),
                ('Павел', 999.99),
                ('Дмитрий', 450.10),
                ('Анна', 730.00),
                ('Никита', 1300.45);
                """;
        statement.execute(query);
    }

    @Test
    @DisplayName("Проверка баланса")
    void testUpdateBalance() throws SQLException {
        BigDecimal balance = accountDAO.findById(2).getBalance();
        BigDecimal n = BigDecimal.valueOf(10);
        BigDecimal sumUpd = balance.add(n);
        accountDAO.updateBalance(2, sumUpd);
        assertEquals(sumUpd, accountDAO.findById(2).getBalance());
    }

    @Test
    @DisplayName("Проверка добавления клиента")
    void testAddAccount() throws SQLException {
        Account client = new Account(11, "Ron", new BigDecimal("777"));
        accountDAO.save(client);
        assertEquals(client, accountDAO.findById(11));
    }

}