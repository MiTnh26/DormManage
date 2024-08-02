package Mapper;

import Entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentMap implements RowMapper<Payment>{
    @Override
    public Payment mapRow(ResultSet rs) {
        Payment.PaymentBuilder payment = Payment.builder();
        try{
            payment.paymentId(rs.getInt("paymentId"))
                    .createDate(rs.getTimestamp("createDate"))
                    .roomName(rs.getString("roomName"))
                    .status(rs.getString("status"))
                    .totalAmount(rs.getLong("totalAmount"))
                    .totalAmountPaid(rs.getLong("totalAmountPaid"))
                    .totalAmountRemain(rs.getLong("totalAmountRemain"))
                    .rollId(rs.getString("rollId"))
                    .billId(rs.getInt("billId"))
                    .type(rs.getInt("type"))
                    .bed(rs.getInt("bed"))
                    .description(rs.getString("description"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return payment.build();
    }
}
