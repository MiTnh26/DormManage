package Mapper;

import Entity.ElectricWaterUsage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ElectricWaterUsageMap implements RowMapper<ElectricWaterUsage> {
    @Override
    public ElectricWaterUsage mapRow(ResultSet rs) {
        ElectricWaterUsage.ElectricWaterUsageBuilder builder = ElectricWaterUsage.builder();
        try {
            builder.id(rs.getInt("id"))
                    .electricNumber(rs.getLong("electricNumber"))
                    .month(rs.getInt("month"))
                    .floor(rs.getInt("floor"))
                    .roomName(rs.getString("room"))
                    .term(rs.getString("term"))
                    .waterNumber(rs.getLong("waterNumber"))
                    .domName(rs.getString("domName"))
                    .year(rs.getInt("year"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return builder.build();
    }
}
