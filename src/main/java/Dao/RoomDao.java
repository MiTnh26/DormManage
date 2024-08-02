package Dao;

import Dao.GenericDao.GenericDao;
import Entity.Room;

import java.util.List;

public interface RoomDao extends GenericDao<Room> {
    Room findByRoomNameAndFloor(String roomName, Integer floor);

    void update(Room Room);

    List<Room> findAll();

    Room getByRoomName(String roomName);
}
