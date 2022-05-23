package com.epam.demo.models.dto;

import com.epam.demo.models.Topic;
import lombok.Data;
import java.util.List;

@Data
public class TopicsDto {
    private List<Topic> topics;
}
