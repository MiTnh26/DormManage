package Mapper;

import Entity.Dom;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DomMap implements RowMapper<Dom> {
    @Override
    public Dom mapRow(ResultSet rs) {
        Dom.DomBuilder dorm = Dom.builder();
        try {
            dorm.domId(rs.getInt("domId"))
                    .domName(rs.getString("domName"))
                    .numberOfFloor(rs.getInt("numberOfFloor"))
                    .domGender(rs.getString("domGender"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dorm.build();
    }
}
