package com.kpi.demo.service.impl;

import com.kpi.demo.exception.NotFoundByIdException;
import com.kpi.demo.models.Article;
import com.kpi.demo.models.Publication;
import com.kpi.demo.repository.ArticleRepository;
import com.kpi.demo.repository.PublicationRepository;
import com.kpi.demo.service.ArticleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    private static final Logger LOGGER = Logger.getLogger(ArticleServiceImpl.class);
    private final ArticleRepository articleRepository;
    private final PublicationRepository publicationRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, PublicationRepository publicationRepository) {
        this.articleRepository = articleRepository;
        this.publicationRepository = publicationRepository;
    }

    @Override
    public Article save(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public boolean delete(Long articleId) {
        articleRepository.deleteById(articleId);
        return true;
    }

    @Override
    public Article findById(Long articleId) {
        return articleRepository.findById(articleId)
                .orElseThrow(() -> new NotFoundByIdException("The article can't be found by id"));
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public Article saveIntoPublication(Article article, Long id) {
        Publication publication = publicationRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException("The publication can't be found by id"));
        LOGGER.info("Publication was successfully found by id");
        publication.getArticles().add(article);
        publicationRepository.save(publication);
        LOGGER.info("Article was successfully added into publication");
        return article;
    }

    @Override
    public boolean deleteFromPublication(Long id, long publicationId) {
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new NotFoundByIdException("The publication can't be found by id"));
        LOGGER.info("Publication was successfully found by id");
        Article article = findById(id);
        LOGGER.info("Article was successfully found by id");
        if (publication.getArticles().remove(article)) {
            publicationRepository.save(publication);
            LOGGER.info("Article was successfully removed from publication");
            return true;
        }
        return false;
    }

    @Override
    public Page<Article> findAllPaginated(Long id, String keyword, int page, int size, String sortField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        if (keyword == null) {
            return articleRepository.findAllByPublicationId(id, pageable);
        }
        return articleRepository.findByTitleContainingAndPublicationId(id, keyword, pageable);
    }
}
