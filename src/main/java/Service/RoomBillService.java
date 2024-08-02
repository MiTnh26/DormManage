package Service;

import Entity.ElectricWaterUsage;
import Entity.RoomBill;

import java.util.List;

public interface RoomBillService {
    RoomBill getById(Integer id);

    RoomBill getByRollNameAndTermAndYear(String rollName, String term, int year);

    List<RoomBill> getByTermAndYear(String term, int year);

    void saveAll(List<RoomBill> roomBills);

    List<RoomBill> getAll();
    List<RoomBill> getAll(String param, int offset, int limit);
    List<RoomBill> getByRoomNameAndTermAndYear(String roomName, String term, int year);

    void updateStatus(Integer id, String status);
}
