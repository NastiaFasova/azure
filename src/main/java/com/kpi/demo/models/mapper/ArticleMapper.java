package com.kpi.demo.models.mapper;

import com.kpi.demo.models.Article;
import com.kpi.demo.models.dto.request.ArticleRequestDto;
import com.kpi.demo.models.dto.response.ArticleResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {
    public ArticleResponseDto getArticleResponseDto(Article article) {
        ArticleResponseDto articleResponseDto = new ArticleResponseDto();
        articleResponseDto.setTitle(article.getTitle());
        articleResponseDto.setText(article.getText());
        articleResponseDto.setId(article.getId());
        articleResponseDto.setDate(article.getDate());
        return articleResponseDto;
    }

    public Article getArticle(ArticleRequestDto articleRequestDto) {
        Article article = new Article();
        article.setTitle(articleRequestDto.getTitle());
        article.setText(articleRequestDto.getText());
        article.setId(articleRequestDto.getId());
        article.setDate(articleRequestDto.getDate());
        return article;
    }
}
