package com.epam.demo.controller;

import com.epam.demo.models.dto.request.TopicRequestDto;
import com.epam.demo.models.dto.response.TopicResponseDto;
import com.epam.demo.models.mapper.TopicMapper;
import com.epam.demo.service.TopicService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class TopicController {
    private static final Logger LOGGER = Logger.getLogger(TopicController.class);
    private final TopicService topicService;
    private final TopicMapper topicMapper;

    @Autowired
    public TopicController(TopicService topicService, TopicMapper topicMapper) {
        this.topicService = topicService;
        this.topicMapper = topicMapper;
    }

    @GetMapping("/create/topic")
    public String showNewTopicForm(Model model) {
        TopicResponseDto topic = new TopicResponseDto();
        model.addAttribute("topic", topic);
        return "create/topic";
    }

    @PostMapping("/topic")
    public String save(@Validated @ModelAttribute("topic") TopicRequestDto topic, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create/topic";
        }
        topicService.save(topicMapper.getTopic(topic));
        LOGGER.info("Topic was successfully saved");
        return "redirect:/";
    }
}
