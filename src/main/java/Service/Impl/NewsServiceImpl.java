package Service.Impl;

import Dao.Impl.NewsDaoImpl;
import Dao.NewsDao;
import Entity.News;
import Service.NewsService;

import java.util.List;

public class NewsServiceImpl implements NewsService {
    private final NewsDao newsDao = new NewsDaoImpl();
    @Override
    public List<News> getAll(Integer offset, Integer limit) {
        return newsDao.findAll(offset, limit);
    }

    @Override
    public List<News> getAll() {
        return newsDao.findAll();
    }

    @Override
    public News getById(int id) {
        return newsDao.findById(id);
    }

    @Override
    public void insert(News news) {
        newsDao.insert(news);
    }

    @Override
    public void update(News news) {
        newsDao.update(news);
    }

    @Override
    public void delete(News news) {
        newsDao.delete(news);
    }
}
