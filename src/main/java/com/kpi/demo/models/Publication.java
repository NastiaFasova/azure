package com.epam.demo.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "publications")
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Double price;
    private String text;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Article> articles;
    @ManyToOne
    private Topic topic;

    public Publication(String title, Double price, Topic topic) {
        this.title = title;
        this.price = price;
        this.topic = topic;
    }
}
