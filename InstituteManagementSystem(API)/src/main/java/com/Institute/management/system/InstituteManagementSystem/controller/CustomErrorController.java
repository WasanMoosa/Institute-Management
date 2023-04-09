package com.Institute.management.system.InstituteManagementSystem.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CustomErrorController implements ErrorController {


    @GetMapping(path = "/error")
    public String getErrorHtml(Model model) {
        return "error";
    }

}
