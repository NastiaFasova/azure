package com.kpi.demo.controller;

import com.kpi.demo.models.User;
import com.kpi.demo.models.dto.BillDto;
import com.kpi.demo.models.mapper.BillMapper;
import com.kpi.demo.service.BillService;
import com.kpi.demo.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class BillController {
    private static final Logger LOGGER = Logger.getLogger(BillController.class);
    private final BillService billService;
    private final BillMapper billMapper;
    private final UserService userService;

    @Autowired
    public BillController(BillService billService, BillMapper billMapper, UserService userService) {
        this.billService = billService;
        this.billMapper = billMapper;
        this.userService = userService;
    }

    @PostMapping("/bill")
    public String save(@Validated @ModelAttribute("bill") BillDto bill, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "bill";
        }
        billService.save(billMapper.getBill(bill));
        LOGGER.info("Bill was successfully saved");
        return "redirect:/bill";
    }

    @GetMapping("/bill")
    public String viewBill(Model model, Authentication authentication) {
        User user = userService.getByEmail(authentication.getName());
        LOGGER.info("User was successfully retrieved by email");
        model.addAttribute("bill", billMapper.getBillDto(user.getBill()));
        return "bill";
    }
}
