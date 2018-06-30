package ru.innopolis.stc9.t1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.stc9.t1.service.GroupService;

@Controller
public class DispatcherController {
    @Autowired
    private GroupService groupService;

    @RequestMapping("/")
    public String getRootPage(Model model) {
        return "index";
    }

    @RequestMapping("/groups")
    public String getGroupsPage(Model model) {
        model.addAttribute("arrayGroup", groupService.getAllGroups());
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

}
