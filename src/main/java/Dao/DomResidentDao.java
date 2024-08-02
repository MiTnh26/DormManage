package Dao;

import Dao.GenericDao.GenericDao;
import Dto.UsagePersonalDto;
import Entity.Dom;
import Entity.DomResident;

import java.util.List;

public interface DomResidentDao extends GenericDao<DomResident> {
    DomResident findById(Integer id);
    DomResident findByRollIdAndSemesterAndYear(String rollId, String semester, int year);

    void update(DomResident domResident);

    List<DomResident> findAll();
    List<DomResident> findAll(String param, int offset, int limit);

    List<DomResident> getByRollId(String rollId);

    int countUserInRoomAndTermAndYear(String room, String term, int year);

    List<DomResident> getByTermAndYear(String term, int year);
    List<DomResident> getByListRollIdAndTermAndYear(List<String> rollIds, String term, int year);
    List<DomResident> getByPreMonthAndYear(int month, int year);

    void save(DomResident domResident);

    int getUsagePersonal(String term, String roomName);

    public List<DomResident> getByRollIdAndYear(String rollId, int year);
}
