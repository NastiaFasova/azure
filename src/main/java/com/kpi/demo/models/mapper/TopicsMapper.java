package com.kpi.demo.models.mapper;

import com.kpi.demo.models.Topic;
import com.kpi.demo.models.dto.TopicsDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TopicsMapper {
    public TopicsDto getTopicsDto(List<Topic> topics) {
        TopicsDto topicsDto = new TopicsDto();
        topicsDto.setTopics(topics);
        return topicsDto;
    }
}
