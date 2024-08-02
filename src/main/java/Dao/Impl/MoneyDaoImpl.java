package Dao.Impl;

import Dao.GenericDao.Impl.GenericDaoImpl;
import Dao.MoneyDao;
import Entity.Money;
import Mapper.MoneyMap;

import java.util.List;

public class MoneyDaoImpl extends GenericDaoImpl<Money> implements MoneyDao {
    @Override
    public Money getById(Integer moneyId) {
        String sql = "SELECT * FROM Money WHERE moneyId = ? ";
        return query(sql, new MoneyMap(), moneyId).stream().findFirst().orElse(null);
    }

    @Override
    public void update(Money money) {
        String sql = "UPDATE Money SET amount = ? WHERE moneyId = ?";
        update(sql, money.getAmount(), money.getMoneyId());
    }

    @Override
    public List<Money> findAll() {
        String sql = "SELECT * FROM Money";
        return query(sql, new MoneyMap());
    }

    @Override
    public List<Money> getByType(String type) {
        String sql = "SELECT * FROM Money WHERE moneyType = ? ";
        return query(sql, new MoneyMap(), type);
    }

    @Override
    public List<Money> getByListType(List<String> type) {
        StringBuilder sql = new StringBuilder("SELECT * FROM Money WHERE moneyType IN (");
        for (int i = 0; i < type.size(); i++) {
            sql.append("'").append(type.get(i)).append("'");
            if (i != type.size() - 1) {
                sql.append(",");
            }
        }
        sql.append(")");

        return query(sql.toString(), new MoneyMap());
    }

    @Override
    public Money getByMoneyTypeAndRoomType(String moneyType, String roomType) {
        String sql = "SELECT * FROM Money WHERE moneyType = ? AND roomType = ? ";
        return query(sql, new MoneyMap(), moneyType, roomType).stream().findFirst().orElse(null);
    }

}
