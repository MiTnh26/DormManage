package Service.Impl;

import Dao.BedDao;
import Dao.Impl.BedDaoImpl;
import Entity.Bed;
import Service.BedService;

import java.util.List;

public class BedServiceImpl implements BedService {

    private final BedDao bedDao = new BedDaoImpl();
    @Override
    public List<Bed> getAll() {
        return bedDao.findAll();
    }

    @Override
    public List<Bed> getByDomId(String domId) {
        return bedDao.getByDomId(domId);
    }

    @Override
    public List<Bed> getByRoomTypeAndGender(String type, String gender) {
        return bedDao.getByRoomTypeAndGender(type, gender);
    }

    @Override
    public Bed getByBedAndRoomName(Integer bedId, String roomName) {
        return bedDao.findByBedIdRoomName(bedId, roomName);
    }

    @Override
    public Bed randomBedByFloorAndDomNameAndRoomType(int floor, String domName, String roomType) {
        return bedDao.getRandomByFloorAndDomNameAndRoomType(floor, domName, roomType);
    }

    @Override
    public void updateStatus(Bed bed) {
        bedDao.update(bed);
    }

    @Override
    public Bed getByStudent(String rollId) {
        return bedDao.getBedByStudent(rollId);
    }
}
