package com.kpi.demo.service;

import com.kpi.demo.models.Article;
import org.springframework.data.domain.Page;
import java.util.List;

public interface ArticleService {
    Article save(Article article);

    boolean delete(Long articleId);

    Article findById(Long articleId);

    List<Article> findAll();

    Article saveIntoPublication(Article article, Long id);

    boolean deleteFromPublication(Long id, long publicationId);

    Page<Article> findAllPaginated(Long id, String keyword, int page, int size, String sortField, String sortDir);
}
