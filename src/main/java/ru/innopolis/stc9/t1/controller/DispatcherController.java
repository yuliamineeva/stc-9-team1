package ru.innopolis.stc9.t1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DispatcherController {

    @RequestMapping("/")
    public String getRootPage(Model model) {
        return "index";
    }

    @RequestMapping("/registration")
    public String getRegistrPage() {
        return "registration";
    }

}
