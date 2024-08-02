package Mapper;

import Entity.Bed;

import java.sql.ResultSet;
import java.sql.SQLException;

import Enum.BedStatus;

public class BedMap implements RowMapper<Bed> {
    @Override
    public Bed mapRow(ResultSet rs) {
        Bed.BedBuilder bed = Bed.builder();
        try {
            bed.bedId(rs.getInt("bedId"))
                    .roomName(rs.getString("roomName"))
                    .bedStatus(BedStatus.valueOf(rs.getString("bedStatus")))
                    .floor(rs.getInt("floor"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bed.build();
    }
}
