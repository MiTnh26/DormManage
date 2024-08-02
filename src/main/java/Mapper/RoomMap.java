package Mapper;

import Entity.Room;
import Enum.RoomStatus;
import Enum.Gender;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomMap implements RowMapper<Room> {
    @Override
    public Room mapRow(ResultSet rs) {
        Room.RoomBuilder room = Room.builder();
        try {
            room.roomName(rs.getString("roomName"))
                    .roomType(rs.getString("roomType"))
                    .roomStatus(RoomStatus.valueOf(rs.getString("roomStatus")))
                    .floor(rs.getInt("floor"))
                    .domId(rs.getInt("domId"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return room.build();
    }
}
