package databases.lesson03.DAO;

import databases.lesson03.entity.User;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoJdbcTest {

    private static Connection connection;
    private static Statement statement;
    private UserDaoJdbc userDao;

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
        userDao = new UserDaoJdbc(connection);

        // очищаем таблицы
        statement.execute("TRUNCATE TABLE orders RESTART IDENTITY CASCADE");
        statement.execute("TRUNCATE TABLE users RESTART IDENTITY CASCADE");

        // вставляем пользователей
        statement.execute("""
            INSERT INTO users (name) VALUES
            ('Андрей'),
            ('Мария'),
            ('Сергей')
        """);

        // вставляем заказы
        statement.execute("""
            INSERT INTO orders (user_id, product, amount) VALUES
            (1, 'Ноутбук', 75000.00),
            (1, 'Мышь беспроводная', 2500.00),
            (2, 'Смартфон', 65000.00),
            (2, 'Чехол для телефона', 1500.00),
            (3, 'Игровая приставка', 55000.00)
        """);
    }

    @Test
    @DisplayName("Поиск пользователя по id вместе с заказами")
    void testFindByIdWithOrders() throws SQLException {
        User user = userDao.findByIdWithOrders(1);

        assertNotNull(user);
        assertEquals("Андрей", user.getName());
        assertEquals(2, user.getOrders().size());

        assertTrue(
                user.getOrders().stream().anyMatch(o -> o.getProduct().equals("Ноутбук"))
        );
    }

    @Test
    @DisplayName("Поиск пользователя без заказов")
    void testFindByIdWithNoOrders() throws SQLException {
        // создаём нового пользователя без заказов
        statement.execute("INSERT INTO users (name) VALUES ('Ольга')");

        int newUserId = 4;
        User user = userDao.findByIdWithOrders(newUserId);

        assertNotNull(user);
        assertEquals("Ольга", user.getName());
        assertTrue(user.getOrders().isEmpty());
    }

    @Test
    @DisplayName("Поиск всех пользователей с заказами")
    void testFindAllWithOrders() throws SQLException {
        List<User> users = userDao.findAllWithOrders();

        assertEquals(3, users.size());

        User maria = users.stream()
                .filter(u -> u.getName().equals("Мария"))
                .findFirst()
                .orElseThrow();

        assertEquals(2, maria.getOrders().size());
    }
}
