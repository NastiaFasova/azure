package com.epam.demo.controller;

import com.epam.demo.models.Publication;
import com.epam.demo.models.Topic;
import com.epam.demo.models.dto.TopicsDto;
import com.epam.demo.models.dto.request.PublicationRequestDto;
import com.epam.demo.models.dto.response.PublicationResponseDto;
import com.epam.demo.models.mapper.PublicationMapper;
import com.epam.demo.models.mapper.TopicsMapper;
import com.epam.demo.service.PublicationService;
import com.epam.demo.service.SubscriptionService;
import com.epam.demo.service.TopicService;
import java.util.List;

import com.epam.demo.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class PublicationController {
    private static final Logger LOGGER = Logger.getLogger(PublicationController.class);
    private final PublicationService publicationService;
    private final PublicationMapper publicationMapper;
    private final TopicService topicService;
    private final TopicsMapper topicsMapper;
    private final SubscriptionService subscriptionService;
    private final UserService userService;

    @Autowired
    public PublicationController(PublicationService publicationService, PublicationMapper publicationMapper,
                                 TopicService topicService, TopicsMapper topicsMapper,
                                 SubscriptionService subscriptionService, UserService userService) {
        this.publicationService = publicationService;
        this.publicationMapper = publicationMapper;
        this.topicService = topicService;
        this.topicsMapper = topicsMapper;
        this.subscriptionService = subscriptionService;
        this.userService = userService;
    }

    @GetMapping
    public String viewPublications(Model model, @RequestParam(required = false) String keyword,
                                   @RequestParam(defaultValue = "1") int page,
                                   @RequestParam(defaultValue = "7") int size,
                                   @RequestParam(value = "sortField", defaultValue = "title") String sortField,
                                   @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir) {
        Page<Publication> pageTuts = publicationService.findAllPaginated(keyword, page, size, sortField, sortDir);
        setAttributes(model, keyword, sortField, sortDir, pageTuts);
        return "publications";
    }

    private void setAttributes(Model model, String keyword, String sortField, String sortDir,
                               Page<Publication> pageTuts) {
        List<Publication> publications = pageTuts.getContent();
        model.addAttribute("publications", publications);
        model.addAttribute("currentPage", pageTuts.getNumber() + 1);
        model.addAttribute("totalItems", pageTuts.getTotalElements());
        model.addAttribute("totalPages", pageTuts.getTotalPages());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);
        model.addAttribute("topics", topicService.findAll());
    }


    @GetMapping("/{id}")
    public String getPublicationsByTopic(@PathVariable(value = "id") Long id, Model model,
                                         @RequestParam(required = false) String keyword,
                                         @RequestParam(defaultValue = "1") int page,
                                         @RequestParam(defaultValue = "7") int size,
                                         @RequestParam(value = "sortField", defaultValue = "title") String sortField,
                                         @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir) {
        Page<Publication> pageTuts
                = publicationService.findAllPaginatedByTitle(id, keyword, page, size, sortField, sortDir);
        LOGGER.info("Publications were successfully retrieved by topic");
        setAttributes(model, keyword, sortField, sortDir, pageTuts);
        return "publications";
    }

    @GetMapping("/create")
    public String showNewPublicationForm(Model model) {
        PublicationResponseDto publication = new PublicationResponseDto();
        TopicsDto topics = topicsMapper.getTopicsDto(topicService.findAll());
        LOGGER.info("Topics were successfully retrieved");
        model.addAttribute("topics", topics.getTopics());
        model.addAttribute("publication", publication);
        return "create/publication";
    }

    @PostMapping("/")
    public String save(@Validated @ModelAttribute("publication") PublicationRequestDto publication,
                       BindingResult bindingResult) {
        if (bindingResult != null && bindingResult.hasErrors()) {
            return "create/publication";
        }
        publicationService.save(publicationMapper.getPublication(publication));
        LOGGER.info("Publication was successfully saved");
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@Validated @ModelAttribute("publication") PublicationRequestDto publication,
                       BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("topics", topicService.findAll());
            return "update/publication";
        }
        publicationService.save(publicationMapper.getPublication(publication));
        LOGGER.info("Publication was successfully saved");
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")//TODO delete from publ_subsc
    public String delete(@PathVariable(value = "id") Long id) {
        subscriptionService.removeSubscriptionsWithPublicationId(id);
        LOGGER.info("Publication was successfully removed from subscriptions");
        userService.removeLikesWithPublicationId(id);
        LOGGER.info("Publication was successfully removed from likes");
        publicationService.delete(id);
        LOGGER.info("Publication was successfully removed");
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String showFormForPublicationUpdate(@PathVariable(value = "id") long id, Model model) {
        Publication publication = publicationService.findById(id);
        LOGGER.info("Publication was successfully retrieved by id");
        List<Topic> topics = topicService.findAll();
        LOGGER.info("Topics were successfully retrieved");
        model.addAttribute("topics", topics);
        model.addAttribute("publication", publication);
        return "update/publication";
    }
}
