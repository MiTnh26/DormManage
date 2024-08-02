package Dao;

import Dao.GenericDao.GenericDao;
import Entity.RoomBill;

import java.util.List;

public interface RoomBillDao extends GenericDao<RoomBill> {
    RoomBill findById(Integer id);

    void update(RoomBill RoomBill);

    List<RoomBill> findAll();
    List<RoomBill> findAll(String param, int offset, int limit);

    RoomBill getByRollNameAndTermAndYear(String rollName, String term, int year);
    List<RoomBill> getByRoomNameAndTermAndYear(String roomName, String term, int year);

    List<RoomBill> getByTermAndYear(String term, int year);

    void saveAll(List<RoomBill> roomBills);
    void updateStatus(Integer id, String status);
}
