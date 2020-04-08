package com.example.telegram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    /**
     * Controller need to install start page of app
     * @return index page
     */
    @GetMapping("/")
    public String showHome(){
        return "index";
    }

}
