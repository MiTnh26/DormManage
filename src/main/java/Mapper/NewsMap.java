package Mapper;

import Entity.News;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsMap implements RowMapper<News>{
    @Override
    public News mapRow(ResultSet rs) {
        News.NewsBuilder news = News.builder();
        try{
            news.newsId(rs.getInt("newsId"))
                    .newsTitle(rs.getString("newsTitle"))
                    .newsDetail(rs.getString("newsDetail"))
                    .picture(rs.getString("picture"))
                    .author(rs.getInt("authorId"))
                    .createdTime(rs.getTimestamp("createdTime"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return news.build();
    }
}
