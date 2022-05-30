package com.kpi.demo.service;

import com.kpi.demo.models.Topic;
import java.util.List;

public interface TopicService {
    Topic save(Topic topic);

    boolean delete(Long topicId);

    Topic findById(Long topicId);

    List<Topic> findAll();

}
