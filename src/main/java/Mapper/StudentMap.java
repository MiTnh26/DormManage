package Mapper;

import Entity.Student;
import Enum.StudentStatus;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMap implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs) {
        Student.StudentBuilder student = Student.builder();
        try {
            student.rollId(rs.getString("rollId"))
                    .fullName(rs.getString("fullName"))
                    .gender(rs.getString("gender"))
                    .avatar(rs.getString("avatar"))
                    .studentStatus(StudentStatus.valueOf(rs.getString("status")))
                    .balance(rs.getLong("balance"))
                    .gmail(rs.getString("gmail"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student.build();
    }
}
