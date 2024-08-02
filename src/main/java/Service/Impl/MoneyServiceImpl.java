package Service.Impl;


import Dao.Impl.MoneyDaoImpl;
import Dao.MoneyDao;
import Entity.Money;
import Service.MoneyService;

import java.util.List;

public class MoneyServiceImpl implements MoneyService {
    private final MoneyDao moneyDao = new MoneyDaoImpl();
    @Override
    public List<Money> getAll() {
        return moneyDao.findAll();
    }

    @Override
    public List<Money> getByMoneyType(String type) {
        return moneyDao.getByType(type);
    }

    @Override
    public List<Money> getByListMoneyType(List<String> type) {
        return moneyDao.getByListType(type);
    }

    @Override
    public Money getByMoneyTypeAndRoomType(String moneyType, String roomType) {
        return moneyDao.getByMoneyTypeAndRoomType(moneyType, roomType);
    }

    @Override
    public Money getById(Integer id) {
        return moneyDao.getById(id);
    }

    @Override
    public void update(Money money) {
         moneyDao.update(money);
    }

}
