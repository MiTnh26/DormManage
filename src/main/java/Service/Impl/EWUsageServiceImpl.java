package Service.Impl;

import Dao.EWUsageDao;
import Dao.Impl.EWUsageDaoImpl;
import Entity.ElectricWaterUsage;
import Service.EWUsageService;

import java.util.List;

public class EWUsageServiceImpl implements EWUsageService {
    private final EWUsageDao ewUsageDao = new EWUsageDaoImpl();

    @Override
    public List<ElectricWaterUsage> getByRoomNameAndYearAndTerm(String roomName, int year, String term) {
        return ewUsageDao.getByRoomNameAndYearAndTerm(roomName, year, term);
    }

    @Override
    public List<ElectricWaterUsage> getByRoomNameAndYear(String roomName, int year) {
        return ewUsageDao.getByRoomNameAndYear(roomName, year);
    }

    @Override
    public boolean checkBillByRoomNameAndMonthAndYear(String roomName, int month, int year) {
        return ewUsageDao.checkBillByRoomNameAndMonthAndYear(roomName, month, year);
    }

    @Override
    public void save(ElectricWaterUsage usage) {
        ewUsageDao.save(usage);
    }

    @Override
    public List<ElectricWaterUsage> getByPrevMonthAndYear(int month, int year) {
        return ewUsageDao.getByPrevMonthAndYear(month, year);
    }

    @Override
    public List<ElectricWaterUsage> getByTermAndYear(String term, int year) {
        return ewUsageDao.getByTermAndYear(term, year);
    }
}
