package com.kpi.demo.controller;

import com.kpi.demo.models.User;
import com.kpi.demo.models.dto.PublicationsDto;
import com.kpi.demo.models.mapper.PublicationsMapper;
import com.kpi.demo.models.mapper.UserMapper;
import com.kpi.demo.service.TopicService;
import com.kpi.demo.service.UserService;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger LOGGER = Logger.getLogger(UserController.class);
    private final UserService userService;
    private final TopicService topicService;
    private final UserMapper userMapper;
    private final PublicationsMapper publicationsMapper;

    @Autowired
    public UserController(UserService userService, TopicService topicService, UserMapper userMapper,
                          PublicationsMapper publicationsMapper) {
        this.userService = userService;
        this.topicService = topicService;
        this.userMapper = userMapper;
        this.publicationsMapper = publicationsMapper;
    }

    @GetMapping("/block/{id}")
    public String block(@PathVariable(value = "id") long id) {
        userMapper.getUserResponseDto(userService.block(id));
        return "redirect:/user";
    }

    @GetMapping("/unblock/{id}")
    public String unblock(@PathVariable(value = "id") long id) {
        userMapper.getUserResponseDto(userService.unblock(id));
        return "redirect:/user";
    }

    @GetMapping
    public String viewUsers(Model model, @RequestParam(required = false) String keyword,
                            @RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "7") int size,
                            @RequestParam(value = "sortField", defaultValue = "surname") String sortField,
                            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir) {
        List<User> users;
        Page<User> pageTuts = userService.findAllPaginated(keyword, page, size, sortField, sortDir);
        users = pageTuts.getContent();
        model.addAttribute("users", users);
        model.addAttribute("currentPage", pageTuts.getNumber() + 1);
        model.addAttribute("totalItems", pageTuts.getTotalElements());
        model.addAttribute("totalPages", pageTuts.getTotalPages());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);
        return "users";
    }

    @GetMapping("/publications/add/{id}")
    public String addPublication(Authentication authentication, @PathVariable(value = "id") Long id, Model model) {
        User user = userService.getByEmail(authentication.getName());
        LOGGER.info("User was successfully retrieved by email");
        userService.addPublication(id, user.getId());
        LOGGER.info("User successfully liked the publication");
        PublicationsDto publications = publicationsMapper.getPublicationsDto(user.getPublications());
        model.addAttribute("publications", publications.getPublications());
        return "own/likes";
    }

    @GetMapping("/publications")
    public String getLikes(Authentication authentication, Model model) {
        User user = userService.getByEmail(authentication.getName());
        LOGGER.info("User was successfully retrieved by email");
        PublicationsDto publications = publicationsMapper.getPublicationsDto(user.getPublications());
        model.addAttribute("publications", publications.getPublications());
        model.addAttribute("topics", topicService.findAll());
        LOGGER.info("Topics were successfully retrieved");
        return "own/likes";
    }

    @GetMapping("/publications/remove/{id}")
    public String removePublication(Authentication authentication, @PathVariable(value = "id") Long id,
                                          Model model) {
        User user = userService.getByEmail(authentication.getName());
        LOGGER.info("User was successfully retrieved by email");
        userService.removePublication(id, user.getId());
        LOGGER.info("User successfully removed the publication from likes");
        PublicationsDto publications = publicationsMapper.getPublicationsDto(user.getPublications());
        model.addAttribute("publications", publications.getPublications());
        return "own/likes";
    }
}
