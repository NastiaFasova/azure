package com.epam.demo.controller;

import com.epam.demo.models.Subscription;
import com.epam.demo.models.User;
import com.epam.demo.models.dto.PublicationsDto;
import com.epam.demo.models.mapper.PublicationsMapper;
import com.epam.demo.service.SubscriptionService;
import com.epam.demo.service.TopicService;
import com.epam.demo.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/subscription")
public class SubscriptionController {
    private static final Logger LOGGER = Logger.getLogger(SubscriptionController.class);
    private final SubscriptionService subscriptionService;
    private final UserService userService;
    private final TopicService topicService;
    private final PublicationsMapper publicationsMapper;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService, UserService userService,
                                  TopicService topicService, PublicationsMapper publicationsMapper) {
        this.subscriptionService = subscriptionService;
        this.userService = userService;
        this.topicService = topicService;
        this.publicationsMapper = publicationsMapper;
    }

    @GetMapping("/{email}")
    public String getSubscribedPublicationsOfUser(@PathVariable(value = "email") String email, Model model) {
        PublicationsDto publications
                = publicationsMapper.getPublicationsDto(subscriptionService.getPublicationsByUserEmail(email));
        LOGGER.info("Publications were successfully retrieved by user's email");
        model.addAttribute("publications", publications.getPublications());
        User user = userService.getByEmail(email);
        LOGGER.info("User was successfully retrieved by email");
        model.addAttribute("user", user);
        model.addAttribute("topics", topicService.findAll());
        LOGGER.info("Topics were successfully retrieved");
        return "own/subscriptions";
    }

    @GetMapping("/subscribe/{id}")
    public ModelAndView subscribe(@PathVariable(value = "id") Long id, Authentication authentication) {
        ModelAndView mv = new ModelAndView();
        User user = userService.getByEmail(authentication.getName());
        LOGGER.info("User was successfully retrieved by email");
        Subscription subscription = subscriptionService.subscribe(user, id);
        LOGGER.info("User successfully subscribed");
        mv.setViewName("own/subscriptions");
        mv.getModel().put("publications", subscription.getPublications());
        return mv;
    }

    @DeleteMapping("/{id}")
    public String deleteFromSubscriptions(@PathVariable(value = "id") Long id, Model model,
                                          Authentication authentication) {
        User user = userService.getByEmail(authentication.getName());
        LOGGER.info("User was successfully retrieved by email");
        subscriptionService.deleteFromSubscriptions(user, id);
        LOGGER.info("User successfully unsubscribed");
        PublicationsDto publications
                = publicationsMapper.getPublicationsDto(subscriptionService.findById(id).getPublications());
        model.addAttribute("publications", publications);
        return "redirect:/subscription/{email}";
    }
}
