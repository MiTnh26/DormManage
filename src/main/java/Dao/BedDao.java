package Dao;

import Dao.GenericDao.GenericDao;
import Entity.Bed;

import java.util.List;

public interface BedDao extends GenericDao<Bed> {
    Bed findByBedIdRoomName(Integer bedId, String roomName);

    List<Bed> getByDomId(String domId);

    void update(Bed bed);

    List<Bed> findAll();

    List<Bed> getByRoomTypeAndGender(String type, String gender);

    Bed getRandomByFloorAndDomNameAndRoomType(int floor, String domName, String roomType);

    Bed getBedByStudent(String rollId);
}
