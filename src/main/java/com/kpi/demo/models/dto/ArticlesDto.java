package com.kpi.demo.models.dto;

import com.kpi.demo.models.Article;
import lombok.Data;
import java.util.List;

@Data
public class ArticlesDto {
    private List<Article> articleList;
}
