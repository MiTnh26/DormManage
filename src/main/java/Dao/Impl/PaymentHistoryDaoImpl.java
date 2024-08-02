package Dao.Impl;

import Dao.GenericDao.Impl.GenericDaoImpl;
import Dao.PaymentHistoryDao;
import Entity.Payment;
import Mapper.PaymentMap;

import java.util.List;

public class PaymentHistoryDaoImpl extends GenericDaoImpl<Payment> implements PaymentHistoryDao {
    @Override
    public Payment findById(Integer id) {
        String sql = "SELECT * FROM PaymentHistory WHERE paymentId = ?";
        return query(sql, new PaymentMap(), id).stream().findFirst().orElse(null);
    }

    @Override
    public void update(Payment payment) {
        String sql = "UPDATE PaymentHistory SET roomName = ?, totalAmount = ?, totalAmountPaid = ?, totalAmountRemain = ?, status = ?, description = ?, createDate = ? WHERE paymentId = ? ";
        update(sql, payment.getRoomName(), payment.getTotalAmount(), payment.getTotalAmountPaid(), payment.getTotalAmountRemain(),
                payment.getStatus(), payment.getDescription(), payment.getCreateDate(), payment.getPaymentId());
    }

    @Override
    public List<Payment> findAll() {
        String sql = "SELECT * FROM PaymentHistory";
        return query(sql, new PaymentMap());
    }

    @Override
    public List<Payment> getByRollId(String rollId) {
        String sql = "SELECT * FROM PaymentHistory WHERE rollId = ? ORDER BY paymentId desc";
        return query(sql, new PaymentMap(), rollId);
    }

    @Override
    public void savePaymentRoom(Payment payment) {
        String sql = "INSERT INTO PaymentHistory(rollId, term, description, totalAmount, totalAmountPaid, totalAmountRemain, bed, createDate, type, roomName, status) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        insert(sql, payment.getRollId(), payment.getTerm(), payment.getDescription(), payment.getTotalAmount(), payment.getTotalAmountPaid(),
                payment.getTotalAmountRemain(), payment.getBed(), payment.getCreateDate(), payment.getType(), payment.getRoomName(), payment.getStatus());
    }

    @Override
    public void savePaymentEW(Payment payment) {
        String sql = "INSERT INTO PaymentHistory(rollId, term, description, totalAmount, totalAmountPaid, totalAmountRemain, bed, createDate, type, roomName, status, billId) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        insert(sql, payment.getRollId(), payment.getTerm(), payment.getDescription(), payment.getTotalAmount(), payment.getTotalAmountPaid(),
                payment.getTotalAmountRemain(), payment.getBed(), payment.getCreateDate(), payment.getType(), payment.getRoomName(), payment.getStatus(), payment.getBillId());
    }
}
