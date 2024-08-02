package Dao;

import Dao.GenericDao.GenericDao;
import Entity.News;
import Entity.Role;

import java.util.List;

public interface RoleDao extends GenericDao<Role> {
    Role findById(Role id);
    List<Role> findAll();
}
