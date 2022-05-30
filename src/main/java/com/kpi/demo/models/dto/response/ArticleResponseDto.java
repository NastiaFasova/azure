package com.kpi.demo.models.dto.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ArticleResponseDto {
    private Long id;
    private String title;
    private String text;
    private LocalDate date;
}
