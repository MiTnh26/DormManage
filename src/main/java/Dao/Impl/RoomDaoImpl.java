package Dao.Impl;

import Dao.GenericDao.Impl.GenericDaoImpl;
import Dao.RoomDao;
import Entity.Room;
import Mapper.RoomMap;

import java.util.List;

public class RoomDaoImpl extends GenericDaoImpl<Room> implements RoomDao {
    @Override
    public Room findByRoomNameAndFloor(String roomName, Integer floor) {
        String sql = "SELECT * FROM Room WHERE roomName = ? AND floor = ?";
        return query(sql, new RoomMap(), roomName, floor).stream().findFirst().orElse(null);
    }

    @Override
    public void update(Room room) {
        String sql = "UPDATE Room SET roomType = ?, roomStatus = ?, domId = ? WHERE roomName = ? AND floor = ?";
        update(sql, room.getRoomType(), room.getRoomStatus(), room.getDomId(), room.getRoomName(), room.getFloor());
    }

    @Override
    public List<Room> findAll() {
        String sql = "SELECT * FROM Room";
        return query(sql, new RoomMap());
    }

    @Override
    public Room getByRoomName(String roomName) {
        String sql = "SELECT * FROM Room WHERE roomName = ? ";
        return query(sql, new RoomMap(), roomName).stream().findFirst().orElse(null);
    }
}
