package Mapper;

import Dto.UsagePersonalDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsagePersonalMap implements RowMapper<UsagePersonalDto>{
    @Override
    public UsagePersonalDto mapRow(ResultSet rs) {
        UsagePersonalDto.UsagePersonalDtoBuilder builder = UsagePersonalDto.builder();
        try {
            builder.termId(rs.getString("termId"))
                    .numOfUser(rs.getInt("numOfUser"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return builder.build();
    }
}
