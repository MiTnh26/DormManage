package Dao.Impl;

import Dao.GenericDao.Impl.GenericDaoImpl;
import Dao.TermDao;
import Entity.Term;
import Mapper.TermMap;

import java.util.List;

public class TermDaoImpl extends GenericDaoImpl<Term> implements TermDao {
    @Override
    public Term findById(Integer id) {
        String sql = "SELECT * FROM Term WHERE termId = ?";
        return query(sql, new TermMap(), id).stream().findFirst().orElse(null);
    }

    @Override
    public void update(Term term) {
        String sql = "UPDATE Term SET termNam = ? WHERE termId = ?";
        update(sql, term.getTermName(), term.getTermId());
    }

    @Override
    public List<Term> findAll() {
        String sql = "SELECT * FROM Term";
        return query(sql, new TermMap());
    }
}
