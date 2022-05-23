package com.epam.demo.models.mapper;

import com.epam.demo.models.Topic;
import com.epam.demo.models.dto.request.TopicRequestDto;
import com.epam.demo.models.dto.response.TopicResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TopicMapper {
    public TopicResponseDto getTopicResponseDto(Topic topic) {
        TopicResponseDto topicResponseDto = new TopicResponseDto();
        topicResponseDto.setId(topic.getId());
        topicResponseDto.setTitle(topic.getTitle());
        return topicResponseDto;
    }

    public Topic getTopic(TopicRequestDto topicRequestDto) {
        Topic topic = new Topic();
        topic.setTitle(topicRequestDto.getTitle());
        return topic;
    }
}
