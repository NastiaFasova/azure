package com.kpi.demo.models.dto.request;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class TopicRequestDto {
    @NotBlank(message = "Title is mandatory")
    private String title;
}
