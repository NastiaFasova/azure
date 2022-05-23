package com.epam.demo.models.mapper;

import com.epam.demo.models.Article;
import com.epam.demo.models.dto.ArticlesDto;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class ArticlesMapper {
    public ArticlesDto getArticlesDto(List<Article> articles) {
        ArticlesDto articlesDto = new ArticlesDto();
        articlesDto.setArticleList(articles);
        return articlesDto;
    }
}
