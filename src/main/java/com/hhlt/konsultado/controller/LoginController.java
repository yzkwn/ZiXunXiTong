package com.hhlt.konsultado.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/")
    public String login(){
        return  "login";
    }

    @RequestMapping("/index")
    public String loginIndex(){
        return  "index";
    }

}
