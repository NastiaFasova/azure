package com.epam.demo.models.dto.request;

import javax.validation.constraints.NotBlank;

public class PublicationResponseDto {
    @NotBlank(message = "Title is mandatory")
    private String title;
    private Double price;
    private String text;
}
