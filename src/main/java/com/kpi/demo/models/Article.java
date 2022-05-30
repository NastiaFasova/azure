package com.kpi.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name="articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String text;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, fallbackPatterns = { "M/d/yy", "dd.MM.yyyy" })
    private LocalDate date;

    public Article(String title, String text, LocalDate date) {
        this.title = title;
        this.text = text;
        this.date = date;
    }
}
