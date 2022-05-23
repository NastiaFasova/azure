package com.epam.demo.service.impl;

import com.epam.demo.exception.NotFoundByIdException;
import com.epam.demo.models.Publication;
import com.epam.demo.models.Topic;
import com.epam.demo.repository.PublicationRepository;
import com.epam.demo.repository.TopicRepository;
import com.epam.demo.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;
    private final PublicationRepository publicationRepository;

    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository, PublicationRepository publicationRepository) {
        this.topicRepository = topicRepository;
        this.publicationRepository = publicationRepository;
    }

    @Override
    public Topic save(Topic topic) {
        return topicRepository.save(topic);
    }

    @Override
    public boolean delete(Long topicId) {
        topicRepository.deleteById(topicId);
        return true;
    }

    @Override
    public Topic findById(Long topicId) {
        return topicRepository.findById(topicId)
                .orElseThrow(() -> new NotFoundByIdException("The topic can't be found by id"));
    }

    @Override
    public List<Topic> findAll() {
        return topicRepository.findAll();
    }

}
