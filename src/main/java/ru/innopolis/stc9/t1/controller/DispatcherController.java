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

    @RequestMapping("/groups")
    public String getGroupsPage(Model model) {
        return "groups";
    }

    @RequestMapping("/group_list")
    public String getGroupListPage(Model model) {
        return "group_list";
    }

    @RequestMapping("/registration")
    public String getRegistrPage() {
        return "registration";
    }

    @RequestMapping("/lessons")
    public String getLessonsPage(Model model) {
        return "lessons";
    }

}
