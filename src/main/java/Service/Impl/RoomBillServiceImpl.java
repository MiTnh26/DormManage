package Service.Impl;

import Dao.Impl.RoomBillDaoImpl;
import Dao.RoomBillDao;
import Entity.RoomBill;
import Service.RoomBillService;

import java.util.List;

public class RoomBillServiceImpl implements RoomBillService {
    private final RoomBillDao roomBillDao = new RoomBillDaoImpl();

    @Override
    public RoomBill getById(Integer id) {
        return roomBillDao.findById(id);
    }

    @Override
    public RoomBill getByRollNameAndTermAndYear(String rollName, String term, int year) {
        return roomBillDao.getByRollNameAndTermAndYear(rollName, term, year);
    }

    @Override
    public List<RoomBill> getByTermAndYear(String term, int year) {
        return roomBillDao.getByTermAndYear(term, year);
    }

    @Override
    public void saveAll(List<RoomBill> roomBills) {
        roomBillDao.saveAll(roomBills);
    }

    @Override
    public List<RoomBill> getAll() {
        return roomBillDao.findAll();
    }

    @Override
    public List<RoomBill> getAll(String param, int offset, int limit) {
        return roomBillDao.findAll(param, offset, limit);
    }

    @Override
    public List<RoomBill> getByRoomNameAndTermAndYear(String roomName, String term, int year) {
        return roomBillDao.getByRoomNameAndTermAndYear(roomName, term, year);
    }

    @Override
    public void updateStatus(Integer id, String status) {
        roomBillDao.updateStatus(id, status);
    }
}
