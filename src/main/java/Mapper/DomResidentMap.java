package Mapper;

import Entity.DomResident;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DomResidentMap implements RowMapper<DomResident> {
    @Override
    public DomResident mapRow(ResultSet rs) {
        DomResident.DomResidentBuilder dom = DomResident.builder();
        try {
            dom.residentId(rs.getInt("residentId"))
                    .bedId(rs.getInt("bedId"))
                    .userId(rs.getInt("userId"))
                    .balance(rs.getLong("balance"))
                    .checkInDate(rs.getTimestamp("checkInDate"))
                    .checkOutDate(rs.getTimestamp("checkOutDate"))
                    .termId(rs.getString("termId"))
                    .roomName(rs.getString("roomName"))
                    .rollId(rs.getString("rollId"))
                    .floor(rs.getInt("floor"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dom.build();
    }
}
