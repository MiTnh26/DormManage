package Dao;

import Dao.GenericDao.GenericDao;
import Entity.Dom;
import Entity.News;

import java.util.List;

public interface NewsDao extends GenericDao<News> {
    News findById(Integer id);

    void update(News news);

    List<News> findAll();
    List<News> findAll(Integer offset, Integer limit);

    void insert(News news);

    void delete(News news);
}
