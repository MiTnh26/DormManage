package Service;

import Entity.ElectricWaterUsage;

import java.util.List;

public interface EWUsageService {
    List<ElectricWaterUsage> getByRoomNameAndYearAndTerm(String roomName, int year, String term);
    List<ElectricWaterUsage> getByRoomNameAndYear(String roomName, int year);
    boolean checkBillByRoomNameAndMonthAndYear(String roomName, int month, int year);

    void save(ElectricWaterUsage usage);

    List<ElectricWaterUsage> getByPrevMonthAndYear(int month, int year);
    List<ElectricWaterUsage> getByTermAndYear(String term, int year);
}
