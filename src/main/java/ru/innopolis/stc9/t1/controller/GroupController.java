package ru.innopolis.stc9.t1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc9.t1.pojo.Group;
import ru.innopolis.stc9.t1.service.GroupService;

@Controller
public class GroupController {
    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService){
        this.groupService = groupService;
    }

    @RequestMapping("/groups")
    public String getGroupsPage(Model model) {
        model.addAttribute("arrayGroup", groupService.getAllGroups());
        return "groups/groups";
    }

    @RequestMapping("/group_list")
    public String getGroupListPage(
            @RequestParam(value = "groupId", required = false) String groupId, Model model) {
        if (groupId != null) {
            Group group = groupService.getGroupById(Integer.valueOf(groupId));
            model.addAttribute("group", group);
            model.addAttribute("groupList", group.getUsers());
        }
        return "groups/group_list";
    }

}