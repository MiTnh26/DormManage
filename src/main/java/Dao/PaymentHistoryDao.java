package Dao;

import Dao.GenericDao.GenericDao;
import Entity.Payment;

import java.util.List;

public interface PaymentHistoryDao extends GenericDao<Payment> {
    Payment findById(Integer id);

    void update(Payment payment);

    List<Payment> findAll();

    List<Payment> getByRollId(String rollId);

    void savePaymentRoom(Payment payment);
    void savePaymentEW(Payment payment);
}
