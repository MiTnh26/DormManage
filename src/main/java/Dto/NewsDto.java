package Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Builder
@ToString
public class NewsDto {
    private Integer newsId;
    private String newsTitle;
    private String newsDetail;
    private Timestamp createdTime;
    private String author;
    private int authorId;
}
