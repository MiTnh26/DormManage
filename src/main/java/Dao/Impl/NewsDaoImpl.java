package Dao.Impl;

import Dao.GenericDao.GenericDao;
import Dao.GenericDao.Impl.GenericDaoImpl;
import Dao.NewsDao;
import Entity.News;
import Mapper.NewsMap;

import java.util.List;

public class NewsDaoImpl extends GenericDaoImpl<News> implements NewsDao {
    @Override
    public News findById(Integer id) {
        String sql = "SELECT * FROM News WHERE newsId = ?";
        return query(sql, new NewsMap(), id).stream().findFirst().orElse(null);
    }

    @Override
    public void update(News news) {
        String sql = "UPDATE News SET newsTitle = ?, newsDetail = ? WHERE newsId = ?";
        update(sql, news.getNewsTitle(), news.getNewsDetail(), news.getNewsId());
    }

    @Override
    public List<News> findAll() {
        String sql = "SELECT * FROM News";
        return query(sql, new NewsMap());
    }

    @Override
    public List<News> findAll(Integer offset, Integer limit) {
        StringBuilder sql = new StringBuilder("SELECT * FROM News ORDER BY newsId Desc");
        sql.append(" OFFSET ").append(offset).append(" ROWS FETCH NEXT ").append(limit).append(" ROWS ONLY");
        return query(sql.toString(), new NewsMap());
    }

    @Override
    public void insert(News news) {
        String sql = "INSERT INTO News (newsTitle, newsDetail, createdTime, authorId) VALUES (?, ?, ?, ?)";
        insert(sql, news.getNewsTitle(), news.getNewsDetail(), news.getCreatedTime(), news.getAuthor());
    }

    @Override
    public void delete(News news) {
        String sql = "DELETE FROM News WHERE newsId = ?";
        delete(sql, news.getNewsId());
    }
}
