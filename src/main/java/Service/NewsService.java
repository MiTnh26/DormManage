package Service;

import Entity.News;

import java.util.List;

public interface NewsService {
    List<News> getAll(Integer offset, Integer limit);
    List<News> getAll();
    News getById(int id);

    void insert(News news);

    void update(News news);

    void delete(News news);
}
