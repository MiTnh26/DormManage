package Mapper;

import Entity.Money;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MoneyMap implements RowMapper<Money>{

    @Override
    public Money mapRow(ResultSet rs) {
        Money.MoneyBuilder money = Money.builder();
        try {
            money.moneyId(rs.getInt("moneyId"))
                    .moneyType(rs.getString("moneyType"))
                    .bedTotal(rs.getInt("bedTotal"))
                    .amount(rs.getLong("amount"))
                    .roomType(rs.getString("roomType"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return money.build();
    }
}
