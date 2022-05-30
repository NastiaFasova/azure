package com.kpi.demo.models.dto.request;

import com.kpi.demo.validation.DateValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleRequestDto {
    private Long id;
    @NotBlank(message = "Title is mandatory")
    private String title;
    @NotBlank(message = "Text is mandatory")
    private String text;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, fallbackPatterns = { "M/d/yy", "dd.MM.yyyy" })
    @DateValidation
    private LocalDate date;

    public ArticleRequestDto(String title, String text, LocalDate date) {
        this.text = text;
        this.title = title;
        this.date = date;
    }
}
