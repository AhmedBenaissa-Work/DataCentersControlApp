package com.example.demo3.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class MvCController {

    private static final Logger LOG = LoggerFactory.getLogger(MvCController.class);

    @GetMapping(value = "/login")
    public String login() {
        LOG.info("/login");

        LOG.info("Return login");

        //return login.html located in /resources/templates
        return "login2";
    }
}