package com.kpi.demo.controller;

import com.kpi.demo.models.Article;
import com.kpi.demo.models.dto.ArticlesDto;
import com.kpi.demo.models.dto.request.ArticleRequestDto;
import com.kpi.demo.models.dto.response.ArticleResponseDto;
import com.kpi.demo.models.dto.response.PublicationResponseDto;
import com.kpi.demo.models.mapper.ArticleMapper;
import com.kpi.demo.models.mapper.ArticlesMapper;
import com.kpi.demo.models.mapper.PublicationMapper;
import com.kpi.demo.service.ArticleService;
import com.kpi.demo.service.PublicationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
public class ArticleController {
    private static final Logger LOGGER = Logger.getLogger(ArticleController.class);
    private final ArticleService articleService;
    private final ArticleMapper articleMapper;
    private final ArticlesMapper articlesMapper;
    private final PublicationMapper publicationMapper;
    private final PublicationService publicationService;

    @Autowired
    public ArticleController(ArticleService articleService, ArticleMapper articleMapper,
                             ArticlesMapper articlesMapper, PublicationMapper publicationMapper,
                             PublicationService publicationService) {
        this.articleService = articleService;
        this.articleMapper = articleMapper;
        this.articlesMapper = articlesMapper;
        this.publicationMapper = publicationMapper;
        this.publicationService = publicationService;
    }

    @GetMapping("/article/{id}")
    public String viewArticles(@PathVariable(value = "id") Long id, Model model,
                               @RequestParam(required = false) String keyword,
                               @RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "7") int size,
                               @RequestParam(value = "sortField", defaultValue = "title") String sortField,
                               @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir) {
        Page<Article> pageTuts = articleService.findAllPaginated(id, keyword, page, size, sortField, sortDir);
        ArticlesDto articlesDto = articlesMapper.getArticlesDto(pageTuts.getContent());
        model.addAttribute("articles", articlesDto.getArticleList());
        model.addAttribute("currentPage", pageTuts.getNumber() + 1);
        model.addAttribute("totalItems", pageTuts.getTotalElements());
        model.addAttribute("totalPages", pageTuts.getTotalPages());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);
        LOGGER.info("Topics were successfully retrieved");
        model.addAttribute("id", id);
        return "articles";
    }

    @GetMapping("/article/{id}/read/{article-id}")
    public String readArticle(@PathVariable(value = "article-id") Long articleId,
                              @PathVariable(value = "id") Long publicationId,
                              Model model) {
        ArticleResponseDto articleResponseDto = articleMapper.getArticleResponseDto(articleService.findById(articleId));
        PublicationResponseDto publicationResponseDto
                = publicationMapper.getPublicationResponseDto(publicationService.findById(publicationId));
        model.addAttribute("article", articleResponseDto);
        model.addAttribute("publication", publicationResponseDto);
        return "read/article";
    }

    @PostMapping("/article/{id}")
    public String saveIntoPublication(@Validated @ModelAttribute("article") ArticleRequestDto article,
                                      BindingResult bindingResult,
                                      @PathVariable(value = "id") Long id) {
        if (bindingResult != null && bindingResult.hasErrors()) {
            return "create/article";
        }
        articleService.saveIntoPublication(articleMapper.getArticle(article), id);
        LOGGER.info("Article was successfully added into publication");
        return "redirect:/article/{id}";
    }

    @PostMapping("/update/article")
    public String update(@Validated @ModelAttribute("article") ArticleRequestDto article,
                                      BindingResult bindingResult, Model model) {
        if (bindingResult != null && bindingResult.hasErrors()) {
            model.addAttribute("article", article);
            LOGGER.info(article);
            return "update/article";
        }
        articleService.save(articleMapper.getArticle(article));
        LOGGER.info("Article was successfully updated");
        return "redirect:/";
    }

    @PostMapping("/article/save/{id}")
    public String save(@ModelAttribute("article") @Valid ArticleRequestDto article,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create/article";
        }
        articleService.save(articleMapper.getArticle(article));
        LOGGER.info("Article was successfully saved");
        return "redirect:/article/read/{article-id}";
    }

    @GetMapping("/create/article/{id}")
    public String showNewArticleForm(@PathVariable(value = "id") Long id, Model model) {
        ArticleResponseDto article = new ArticleResponseDto();
        model.addAttribute("article", article);
        model.addAttribute("id", id);
        return "create/article";
    }

    @GetMapping("/delete/{publication-id}/article/{id}")
    public String deleteFromPublication(@PathVariable(value = "id") Long id,
                                        @PathVariable(value = "publication-id") long publicationId) {
        articleService.deleteFromPublication(id, publicationId);
        LOGGER.info("Article was successfully removed from publication");
        return "redirect:/article/{id}";
    }

    @GetMapping("/update/article/{id}")
    public String showFormForArticleUpdate(@PathVariable(value = "id") long id,
                                               Model model) {
        ArticleResponseDto article = articleMapper.getArticleResponseDto(articleService.findById(id));
        LOGGER.info("Article was successfully retrieved by id");
        LOGGER.info(article);
        model.addAttribute("article", article);
        return "update/article";
    }
}
