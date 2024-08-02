package Service.Impl;

import Dao.DomDao;
import Dao.Impl.DomDaoImpl;
import Entity.Dom;
import Service.DomService;

import java.util.List;

public class DomServiceImpl implements DomService {

    private final DomDao domDao = new DomDaoImpl();
    public DomServiceImpl() {
    }

    @Override
    public Dom findById(Integer id) {
        return domDao.findById(id);
    }

    @Override
    public List<Dom> getAll() {
        return domDao.findAll();
    }
}
