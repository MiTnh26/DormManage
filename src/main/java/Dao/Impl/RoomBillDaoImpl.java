package Dao.Impl;

import Dao.GenericDao.Impl.GenericDaoImpl;
import Dao.RoomBillDao;
import Entity.RoomBill;
import Mapper.RequestMap;
import Mapper.RoomBillMap;

import java.util.List;

public class RoomBillDaoImpl extends GenericDaoImpl<RoomBill> implements RoomBillDao {
    @Override
    public RoomBill findById(Integer id) {
        String sql = "SELECT * FROM RoomBill WHERE billId = ?";
        return query(sql, new RoomBillMap(), id).stream().findFirst().orElse(null);
    }

    @Override
    public void update(RoomBill roomBill) {
        String sql = "UPDATE RoomBill SET electricMoney =?, waterMoney = ?, roomName = ?, billStatus = ? , totalAmount = ?, description = ?, dayCreate = ? WHERE billId = ?";
        update(sql, roomBill.getElectricMoney(), roomBill.getWaterMoney(), roomBill.getRoomName(),
                roomBill.getBillStatus(), roomBill.getTotalAmount(), roomBill.getDescription(), roomBill.getBillId());
    }

    @Override
    public List<RoomBill> findAll() {
        String sql = "SELECT * FROM RoomBill";
        return query(sql, new RoomBillMap());
    }

    @Override
    public List<RoomBill> findAll(String param, int offset, int limit) {
        StringBuilder sql = new StringBuilder("SELECT * FROM RoomBill Where rollName LIKE '%");
        sql.append(param).append("%' OR roomName LIKE '%").append(param)
                .append("%' OR term LIKE '%").append(param).append("%' ORDER BY dayCreated desc ")
                .append(" OFFSET ").append(offset).append(" ROWS FETCH NEXT ").append(limit).append(" ROWS ONLY");
        return query(sql.toString(), new RoomBillMap());
    }

    @Override
    public RoomBill getByRollNameAndTermAndYear(String rollName, String term, int year) {
        String sql = "SELECT * FROM RoomBill WHERE rollName = ? AND term = ? AND year = ?";
        return query(sql, new RoomBillMap(), rollName, term, year).stream().findFirst().orElse(null);
    }

    @Override
    public List<RoomBill> getByRoomNameAndTermAndYear(String roomName, String term, int year) {
        String sql = "SELECT * FROM RoomBill WHERE roomName = ? AND term = ? AND year = ?";
        return query(sql, new RoomBillMap(), roomName, term, year);
    }

    @Override
    public List<RoomBill> getByTermAndYear(String term, int year) {
        String sql = "SELECT * FROM RoomBill WHERE term = ? AND year = ?";
        return query(sql, new RoomBillMap(), term, year);
    }

    @Override
    public void saveAll(List<RoomBill> roomBills) {
        for (RoomBill room : roomBills) {
            String sql = "INSERT INTO RoomBill(roomName, rollName, electricMoney, waterMoney, dayCreated, billStatus, totalAmount, electricNumber, waterNumber, term, year, description) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
            insert(sql, room.getRoomName(), room.getRollName(), room.getElectricMoney(), room.getWaterMoney(), room.getDayCreate(), room.getBillStatus(),
                    room.getTotalAmount(), room.getElectricNumber(), room.getWaterNumber(), room.getTerm(), room.getYear(), room.getDescription());
        }
    }

    @Override
    public void updateStatus(Integer id, String status) {
        String sql = "UPDATE RoomBill SET billStatus = ? WHERE billId = ?";
        update(sql, status, id);
    }


}
