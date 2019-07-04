package com.a1nu.test.controller;

import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.a1nu.test.entity.Selector;
import com.a1nu.test.entity.User;
import com.a1nu.test.service.SelectorService;
import com.a1nu.test.service.UserService;

@Controller
public class MainController {
    private SelectorService selectorService;
    private UserService userService;
    
    public MainController(SelectorService selectorService, UserService userService) {
        this.selectorService = selectorService;
        this.userService = userService;
    }
    
    @GetMapping(value = {"/", "/index"})
    public ModelAndView home(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Integer userId = (Integer) session.getAttribute("userId");
        User existingUser = this.userService.getUserById(userId);
        if (existingUser != null) {
            modelAndView.addObject("editable", true);
            modelAndView.addObject("user", existingUser);
        } else {
            User user = new User();
            modelAndView.addObject("user", user);
        }
        
        modelAndView.setViewName("index");
        return modelAndView;
    }
    
    @PostMapping(value = "/")
    public ModelAndView saveUser(User user, BindingResult bindingResult, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        List<Selector> selectors = selectorService.getSelectors();
        List<Selector> newSelectors = new ArrayList<>();
    
    
        if (session.getAttribute("userId") != null) {
            modelAndView.addObject("editable", true);
            if (!user.isEdit()) {
                User newUser = new User();
                user.setId(newUser.getId());
            } else {
                user.setId((Integer) session.getAttribute("userId"));
            }
        }

        
        if (!user.isAgreement()) {
            bindingResult.rejectValue("agreement", "error.agreement", "you need to agree with terms");
        }
        
        if (user.getName() == null) {
            bindingResult.rejectValue("name", "error.name", "name should not be empty");
        }
    
        if (user.getSelectorIds().isEmpty()) {
            bindingResult.rejectValue("selectorIds", "error.selectorIds", "selectors should not be empty");
        } else {
            user.getSelectorIds().forEach(id -> selectors.stream().filter(selector -> id.equals(selector.getId())).forEach(newSelectors::add));
            
            if (newSelectors.isEmpty()) {
                bindingResult.rejectValue("selectorIds", "error.selectorIds", "you are trying to add not existing values");
            }
        }
    
        if (!bindingResult.hasErrors()) {
            user.setSelectors(newSelectors);
            modelAndView.addObject("editable", true);
            session.setAttribute("userId", userService.save(user).getId());
            modelAndView.addObject("successMessage", "User has been added successfully");
            modelAndView.addObject("user", user);
        
        }
    
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
