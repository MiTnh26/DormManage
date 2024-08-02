package Dao.Impl;

import Dao.GenericDao.Impl.GenericDaoImpl;
import Dao.RoleDao;
import Entity.News;
import Entity.Role;
import Mapper.RoleMap;

import java.util.List;

public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {
    @Override
    public Role findById(Role id) {
        String sql = "SELECT * FROM Role WHERE roleId = ?";
        return query(sql, new RoleMap(), id).stream().findFirst().orElse(null);
    }

    @Override
    public List<Role> findAll() {
        String sql = "SELECT * FROM Role";
        return query(sql, new RoleMap());
    }
}
