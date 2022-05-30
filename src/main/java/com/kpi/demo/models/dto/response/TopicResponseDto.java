package com.kpi.demo.models.dto.response;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class TopicResponseDto {
    private Long id;
    @NotBlank
    private String title;
}
