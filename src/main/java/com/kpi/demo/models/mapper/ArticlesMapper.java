package com.kpi.demo.models.mapper;

import com.kpi.demo.models.Article;
import com.kpi.demo.models.dto.ArticlesDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticlesMapper {
    public ArticlesDto getArticlesDto(List<Article> articles) {
        ArticlesDto articlesDto = new ArticlesDto();
        articlesDto.setArticleList(articles);
        return articlesDto;
    }
}
