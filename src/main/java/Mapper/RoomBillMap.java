package Mapper;

import Entity.RoomBill;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomBillMap implements RowMapper<RoomBill>{
    @Override
    public RoomBill mapRow(ResultSet rs) {
        RoomBill.RoomBillBuilder roomBill = RoomBill.builder();
        try {
            roomBill.billId(rs.getInt("billId"))
                    .roomName(rs.getString("roomName"))
                    .waterMoney(rs.getLong("waterMoney"))
                    .electricMoney(rs.getLong("electricMoney"))
                    .billStatus(rs.getString("billStatus"))
                    .totalAmount(rs.getLong("totalAmount"))
                    .description(rs.getString("description"))
                    .dayCreate(rs.getTimestamp("dayCreated"))
                    .electricNumber(rs.getLong("electricNumber"))
                    .waterNumber(rs.getLong("waterNumber"))
                    .year(rs.getInt("year"))
                    .term(rs.getString("term"))
                    .rollName(rs.getString("rollName"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomBill.build();
    }
}
