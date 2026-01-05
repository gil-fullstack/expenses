package com.hightech.expenses.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class DocsController {

    @GetMapping("/")
    @ResponseStatus(HttpStatus.FOUND)
    public String root() {
        return "redirect:/swagger-ui.html";
    }

    @GetMapping("/docs")
    @ResponseStatus(HttpStatus.FOUND)
    public String docs() {
        return "redirect:/swagger-ui.html";
    }
}
