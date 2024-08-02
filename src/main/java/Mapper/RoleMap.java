package Mapper;

import Entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMap implements RowMapper<Role>{
    @Override
    public Role mapRow(ResultSet rs) {
        Role.RoleBuilder role = Role.builder();
        try{
            role.roleId(rs.getInt("roleId"))
                    .roleName(rs.getString("roleName"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return role.build();
    }
}
