package Service.Impl;

import Dao.Impl.PaymentHistoryDaoImpl;
import Dao.PaymentHistoryDao;
import Entity.Payment;
import Service.PaymentService;

import java.util.List;

public class PaymentServiceImpl implements PaymentService {
    private final PaymentHistoryDao paymentHistoryDao = new PaymentHistoryDaoImpl();

    @Override
    public List<Payment> getAll() {
        return paymentHistoryDao.findAll();
    }

    @Override
    public List<Payment> getByRollId(String rollId ) {
        return paymentHistoryDao.getByRollId(rollId);
    }

    @Override
    public Payment getById(Integer id) {
        return paymentHistoryDao.findById(id);
    }

    @Override
    public void savePaymentRoom(Payment payment) {
        paymentHistoryDao.savePaymentRoom(payment);
    }

    @Override
    public void savePaymentEW(Payment payment) {
        paymentHistoryDao.savePaymentEW(payment);
    }
}
