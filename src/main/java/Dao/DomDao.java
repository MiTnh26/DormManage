package Dao;

import Dao.GenericDao.GenericDao;
import Entity.Dom;

import java.util.List;

public interface DomDao extends GenericDao<Dom> {


    Dom findById(Integer id);

    void update(Dom dorm);

    List<Dom> findAll();
}