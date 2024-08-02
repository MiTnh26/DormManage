/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao.Impl;

import Dao.GenericDao.Impl.GenericDaoImpl;
import Dao.DomDao;
import Entity.Dom;
import Mapper.DomMap;

import java.util.List;


/**
 * @author ADMIN
 */
public class DomDaoImpl extends GenericDaoImpl<Dom> implements DomDao {


    @Override
    public Dom findById(Integer id) {
        String sql = "SELECT * FROM Dom WHERE domId = ?";
        return query(sql, new DomMap(), id).stream().findFirst().orElse(null);
    }

    @Override
    public void update(Dom dorm) {
        String sql = "UPDATE Dom SET domName = ?, numberOfFloor = ? WHERE domId = ?";
        update(sql, dorm.getDomName(), dorm.getNumberOfFloor(), dorm.getDomId());
    }

    @Override
    public List<Dom> findAll() {
        String sql = "SELECT * FROM Dom";
        return query(sql, new DomMap());
    }
}
