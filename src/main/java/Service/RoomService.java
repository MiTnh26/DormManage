package Service;

import Entity.Room;

import java.util.List;

public interface RoomService {
    List<Room> getAll();
    Room getByRoomName(String roomName);
}
