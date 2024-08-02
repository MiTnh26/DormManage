package Service.Impl;

import Dao.Impl.RoleDaoImpl;
import Dao.RoleDao;
import Entity.Role;
import Service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao = new RoleDaoImpl();

    @Override
    public List<Role> getAll() {
        List<Role> roleList = roleDao.findAll();
        return roleList;
    }
}
