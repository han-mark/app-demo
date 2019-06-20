package com.bird.business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PageController {

    @RequestMapping("/index")
    public String toIndex(HttpServletRequest request, Model model){
        return "login";
    } 
}