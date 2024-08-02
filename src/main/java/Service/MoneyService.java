package Service;

import Entity.Money;

import java.util.List;

public interface MoneyService {
    List<Money> getAll();
    List<Money> getByMoneyType(String type);
    List<Money> getByListMoneyType(List<String> type);
    Money getByMoneyTypeAndRoomType(String moneyType, String roomType);

    Money getById(Integer id);

    void update(Money money);

}
