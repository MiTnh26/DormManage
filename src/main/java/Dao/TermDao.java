package Dao;

import Dao.GenericDao.GenericDao;
import Entity.Term;

import java.util.List;


public interface TermDao extends GenericDao<Term> {
    Term findById(Integer id);

    void update(Term Term);

    List<Term> findAll();
}
