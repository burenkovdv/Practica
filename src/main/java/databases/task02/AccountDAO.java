package databases.task02;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface AccountDAO {
    Account findById(int id) throws SQLException;
    List<Account> findAll() throws SQLException;
    void updateBalance(int id, BigDecimal newBalance) throws SQLException;
    void save(Account account) throws SQLException;
    void deleteAccount (int id) throws SQLException;
}
