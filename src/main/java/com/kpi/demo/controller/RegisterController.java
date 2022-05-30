package com.kpi.demo.controller;

import com.kpi.demo.models.dto.request.UserRequestDto;
import com.kpi.demo.models.dto.response.UserResponseDto;
import com.kpi.demo.models.mapper.UserMapper;
import com.kpi.demo.service.RegistrationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class RegisterController {
    private static final Logger LOGGER = Logger.getLogger(RegisterController.class);
    private final RegistrationService registrationService;
    private final UserMapper userMapper;

    @Autowired
    public RegisterController(RegistrationService registrationService, UserMapper userMapper) {
        this.registrationService = registrationService;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute("user") UserRequestDto user,
                           BindingResult bindingResult) {
        if (bindingResult != null && bindingResult.hasErrors()) {
            return "register";
        }
        registrationService.register(userMapper.getUser(user));
        LOGGER.info("User was registered");

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showNewLoginForm(Model model) {
        model.addAttribute("user", new UserResponseDto());
        return "login";
    }

    @GetMapping("/register")
    public String showNewRegisterForm(Model model) {
        model.addAttribute("user", new UserResponseDto());
        return "register";
    }
}
