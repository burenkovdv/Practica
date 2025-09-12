package databases.task02;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class Account {
    private Integer id;
    private String ownerName;
    private BigDecimal balance;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;
        return id.equals(account.id) && ownerName.equals(account.ownerName) && balance.doubleValue() == account.balance.doubleValue();
    }
}
