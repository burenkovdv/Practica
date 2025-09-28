package databases.lesson04.DAO;

import databases.lesson03.entity.Order;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderDaoJdbcTest {

    private static Connection connection;
    private static Statement statement;
    private OrderDaoJdbc orderDao;

    @BeforeAll
    static void beforeAll() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/bdv",
                "postgres",
                "12345"
        );
        statement = connection.createStatement();
    }

    @AfterAll
    static void afterAll() throws SQLException {
        statement.close();
        connection.close();
    }

    @BeforeEach
    void setUp() throws SQLException {
        orderDao = new OrderDaoJdbc(connection);
        // очищаем таблицы перед каждым тестом
        statement.execute("TRUNCATE TABLE orders RESTART IDENTITY CASCADE;");
        statement.execute("TRUNCATE TABLE users RESTART IDENTITY CASCADE;");

        // создаём тестовых пользователей
        statement.execute("""
                INSERT INTO users (name) VALUES
                ('Андрей'),
                ('Мария'),
                ('Сергей');
                """);

        // создаём тестовые заказы
        statement.execute("""
                INSERT INTO orders (user_id, product, amount) VALUES
                (1, 'Телефон', 15000.00),
                (1, 'Ноутбук', 75000.50),
                (2, 'Кофемашина', 25000.00),
                (3, 'Пылесос', 12000.00);
                """);
    }

    @Test
    @DisplayName("Найти заказ по ID")
    void testFindById() throws SQLException {
        Order order = orderDao.findById(1);
        assertNotNull(order);
        assertEquals("Телефон", order.getProduct());
        assertEquals(15000, order.getAmount());
    }

    @Test
    @DisplayName("Найти все заказы пользователя")
    void testFindByUserId() throws SQLException {
        List<Order> orders = orderDao.findByUserId(1);
        assertEquals(2, orders.size());
        assertTrue(orders.stream().anyMatch(o -> o.getProduct().equals("Ноутбук")));
    }

    @Test
    @DisplayName("Найти все заказы в системе")
    void testFindAll() throws SQLException {
        List<Order> orders = orderDao.findAll();
        assertEquals(4, orders.size());
    }

    @Test
    @DisplayName("Сохранение нового заказа")
    void testSave() throws SQLException {
        Order newOrder = new Order(0, 2, "Чайник", 1999);
        orderDao.save(newOrder);

        List<Order> orders = orderDao.findByUserId(2);
        assertEquals(2, orders.size());
        assertTrue(orders.stream().anyMatch(o -> o.getProduct().equals("Чайник")));
    }

    @Test
    @DisplayName("Обновление суммы заказа")
    void testUpdateAmount() throws SQLException {
        orderDao.updateAmount(1, 20000);
        Order updated = orderDao.findById(1);
        assertEquals(20000, updated.getAmount());
    }

    @Test
    @DisplayName("Удаление заказа")
    void testDelete() throws SQLException {
        orderDao.delete(4);
        List<Order> orders = orderDao.findAll();
        assertEquals(3, orders.size());
        assertTrue(orders.stream().noneMatch(o -> o.getId() == 4));
    }

    @Test
    @DisplayName("Пользователь без заказов → пустой список")
    void testFindByUserIdNoOrders() throws SQLException {
        List<Order> orders = orderDao.findByUserId(999); // нет такого юзера
        assertTrue(orders.isEmpty());
    }
}
