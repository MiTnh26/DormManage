package Mapper;

import Dto.StudentBedDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentBedDtoMap implements RowMapper<StudentBedDto>{
    @Override
    public StudentBedDto mapRow(ResultSet rs) {
        StudentBedDto.StudentBedDtoBuilder builder = StudentBedDto.builder();
        try {
            builder.rollId(rs.getString("rollId"))
                    .fullName(rs.getString("fullName"))
                    .gender(rs.getString("gender"))
                    .gmail(rs.getString("gmail"))
                    .bed(rs.getInt("bedId"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return builder.build();
    }
}
