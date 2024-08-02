package Mapper;

import Entity.Users;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersMap implements RowMapper<Users>{
    @Override
    public Users mapRow(ResultSet rs) {
        Users.UsersBuilder user = Users.builder();
        try {
            user.userId(rs.getInt("userId"))
                    .gender(rs.getString("gender"))
                    .gmail(rs.getString("gmail"))
                    .password(rs.getString("password"))
                    .roleId(rs.getInt("roleId"))
                    .fullName(rs.getString("fullName"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user.build();
    }
}
