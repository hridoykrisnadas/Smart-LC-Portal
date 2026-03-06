package com.hridoykrisna.smartlcportal.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class WelcomeController {
    @GetMapping({"/", ""})
    public String index() {
        return "Hello World, Welcome to SmartLC Portal !";
    }
}
