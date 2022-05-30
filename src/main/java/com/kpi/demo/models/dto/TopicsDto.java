package com.kpi.demo.models.dto;

import com.kpi.demo.models.Topic;
import lombok.Data;
import java.util.List;

@Data
public class TopicsDto {
    private List<Topic> topics;
}
