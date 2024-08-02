package Service;

import Entity.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> getAll();

    List<Payment> getByRollId(String rollId);

    Payment getById(Integer id);

    void savePaymentRoom(Payment payment);
    void savePaymentEW(Payment payment);
}
