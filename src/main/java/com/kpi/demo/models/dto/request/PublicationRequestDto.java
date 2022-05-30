package com.kpi.demo.models.dto.request;

import com.kpi.demo.models.Topic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicationRequestDto {
    private Long id;
    @NotBlank(message = "Title is mandatory")
    private String title;
    @Positive(message = "Price should be positive")
    private Double price;
    @NotBlank(message = "Text is mandatory")
    private String text;
    private Topic topic;

    public PublicationRequestDto(String title, double price, String text, Topic topic) {
        this.title = title;
        this.price = price;
        this.text = text;
        this.topic = topic;
    }
}
