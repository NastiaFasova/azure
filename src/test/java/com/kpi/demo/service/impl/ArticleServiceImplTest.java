package com.epam.demo.service.impl;

import com.epam.demo.models.Article;
import com.epam.demo.models.Publication;
import com.epam.demo.repository.ArticleRepository;
import com.epam.demo.repository.PublicationRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ArticleServiceImplTest {
    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private PublicationRepository publicationRepository;

    @InjectMocks
    private ArticleServiceImpl articleServiceImpl;
    private Article article;
    private Publication publication;
    private static final Long ID = 1L;

    @Before
    public void setUp() throws Exception {
        article = new Article();
        article.setId(ID);
        publication = new Publication();
        List<Article> articles = new ArrayList<>();
        articles.add(article);
        publication.setArticles(articles);
    }

    @Test
    public void saveIntoPublication() {
        when(publicationRepository.findById(ID)).thenReturn(Optional.ofNullable(publication));
        when(publicationRepository.save(publication)).thenReturn(publication);
        Assert.assertEquals(article, articleServiceImpl.saveIntoPublication(article, ID));
        Assert.assertEquals(article, publication.getArticles()
        .stream()
        .filter(a -> article.equals(a))
        .findFirst()
        .get());
        verify(publicationRepository).findById(ID);
        verify(publicationRepository).save(publication);
    }

    @Test
    public void deleteFromPublication() {
        when(publicationRepository.findById(ID)).thenReturn(Optional.ofNullable(publication));
        when(publicationRepository.save(publication)).thenReturn(publication);
        when(articleRepository.findById(ID)).thenReturn(Optional.ofNullable(article));
        Assert.assertTrue(articleServiceImpl.deleteFromPublication(article.getId(), ID));
        verify(publicationRepository).findById(ID);
        verify(articleRepository).findById(ID);
        verify(publicationRepository).save(publication);
    }
}