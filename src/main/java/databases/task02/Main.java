package databases.task02;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Account> rez = new ArrayList<>();
        AccountDAO it = new AccountDAOJdbc();
        Account client = new Account(4, "Dias", new BigDecimal("5000.0"));
        try {

            it.save(client);
            it.deleteAccount(4);

        } catch (Exception e) {
            System.out.println("Ошибка выполнения!!!");
        }

    }
}
