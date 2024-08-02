package Service;

import Dto.UsagePersonalDto;
import Entity.DomResident;

import java.util.List;

public interface DomResidentService {

    DomResident getById(Integer id);

    List<DomResident> getByRollId(String rollId);

    DomResident getByRollIdAndSemesterAndYear(String rollId, String semester, int year);

    List<DomResident> getBySemesterAndYear(String semester, int year);

    int countUserInRoomAndTermAndYear(String room, String term, int year);

    List<DomResident> getByListRollIdAndTermAndYear(List<String> rollIds, String term, int year);

    List<DomResident> getByPrevMonthAndYear(int month, int year);

    List<DomResident> getAll();

    List<DomResident> getAll(String param, int offset, int limit);

    void save(DomResident domResident);

    int getUsagePersonal(String term, String roomName);

    public List<DomResident> getByRollIdAndYear(String rollId, int year);
}
