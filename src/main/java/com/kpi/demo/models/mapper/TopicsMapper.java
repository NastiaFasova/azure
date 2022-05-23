package com.epam.demo.models.mapper;

import com.epam.demo.models.Topic;
import com.epam.demo.models.dto.TopicsDto;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class TopicsMapper {
    public TopicsDto getTopicsDto(List<Topic> topics) {
        TopicsDto topicsDto = new TopicsDto();
        topicsDto.setTopics(topics);
        return topicsDto;
    }
}
