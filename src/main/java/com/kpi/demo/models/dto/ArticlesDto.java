package com.epam.demo.models.dto;

import com.epam.demo.models.Article;
import lombok.Data;
import java.util.List;

@Data
public class ArticlesDto {
    private List<Article> articleList;
}
