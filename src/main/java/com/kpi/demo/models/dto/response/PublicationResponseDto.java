package com.epam.demo.models.dto.response;

import com.epam.demo.models.Topic;
import lombok.Data;

@Data
public class PublicationResponseDto {
    private Long id;
    private String title;
    private Double price;
    private String text;
    private Topic topic;
}
