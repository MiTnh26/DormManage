package Dao.Impl;

import Dao.EWUsageDao;
import Dao.GenericDao.Impl.GenericDaoImpl;
import Entity.ElectricWaterUsage;
import Mapper.ElectricWaterUsageMap;

import java.util.List;

public class EWUsageDaoImpl extends GenericDaoImpl<ElectricWaterUsage> implements EWUsageDao {
    @Override
    public List<ElectricWaterUsage> getByRoomNameAndYearAndTerm(String roomName, int year, String term) {
        String sql = "SELECT * FROM ElectricWater WHERE room = ? AND year = ? AND term = ?";
        return query(sql, new ElectricWaterUsageMap(), roomName, year, term);
    }

    @Override
    public List<ElectricWaterUsage> getByRoomNameAndYear(String roomName, int year) {
        String sql = "SELECT * FROM ElectricWater WHERE room = ? AND year = ?";
        return query(sql, new ElectricWaterUsageMap(), roomName, year);
    }

    @Override
    public boolean checkBillByRoomNameAndMonthAndYear(String roomName, int month, int year) {
        String sql = "SELECT * FROM ElectricWater WHERE room = ? AND month = ? AND year = ?";
        return query(sql, new ElectricWaterUsageMap(), roomName, month, year).size() > 0;
    }

    @Override
    public void save(ElectricWaterUsage usage) {
        String sql = "INSERT INTO ElectricWater(domName, room, floor, term, month, year, electricNumber, waterNumber) VALUES(?,?,?,?,?,?,?,?)";
        insert(sql, usage.getDomName(), usage.getRoomName(), usage.getFloor(), usage.getTerm(), usage.getMonth(),
                usage.getYear(), usage.getElectricNumber(), usage.getWaterNumber());
    }

    @Override
    public List<ElectricWaterUsage> getByPrevMonthAndYear(int month, int year) {
        String sql = "SELECT * FROM ElectricWater WHERE month = ? AND year = ?";
        return query(sql, new ElectricWaterUsageMap(), month, year);
    }

    @Override
    public List<ElectricWaterUsage> getByTermAndYear(String term, int year) {
        String sql = "SELECT * FROM ElectricWater WHERE term = ? AND year = ?";
        return query(sql, new ElectricWaterUsageMap(), term, year);
    }
}
