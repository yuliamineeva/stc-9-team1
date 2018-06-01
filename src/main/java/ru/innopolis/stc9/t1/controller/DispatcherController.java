package ru.innopolis.stc9.t1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DispatcherController {

    /*@RequestMapping("/login")
    public String getLoginPage(Model model) {
        return "login";
    }*/

    @RequestMapping("/")
    public String getIndexPage(Model model) {
        return "index";
    }

    @RequestMapping("/groups")
    public String getGroupsPage(Model model) {
        return "groups";
    }

}
