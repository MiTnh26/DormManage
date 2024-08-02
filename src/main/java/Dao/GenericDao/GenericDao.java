package Dao.GenericDao;

import Mapper.RowMapper;

import java.util.List;

    public interface GenericDao<T> {
    <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters);

    void update (String sql, Object... parameters);
    void insert (String sql, Object... parameters);
    int count(String sql, Object... parameters);

    void delete(String sql, Object... parameters);
}
