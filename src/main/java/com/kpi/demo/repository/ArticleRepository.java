package com.epam.demo.repository;

import com.epam.demo.models.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("select a from Publication p INNER JOIN p.articles a where p.id =:id")
    Page<Article> findAllByPublicationId(Long id, Pageable pageable);

    @Query("select a from Publication p INNER JOIN p.articles a where p.id =:id and a.title =:keyword")
    Page<Article> findByTitleContainingAndPublicationId(Long id, String keyword, Pageable pageable);
}
