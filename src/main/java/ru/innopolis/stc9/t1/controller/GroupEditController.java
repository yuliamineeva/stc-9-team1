package ru.innopolis.stc9.t1.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc9.t1.pojo.Group;
import ru.innopolis.stc9.t1.service.GroupService;

@Controller
public class GroupEditController{
    private final static Logger logger = Logger.getLogger(GroupEditController.class);
    private GroupService groupService = new GroupService();

    @RequestMapping(value = "/groups/edit", method = RequestMethod.GET)
    public String getGroupEditPage(
            @RequestParam(value = "result", required = false) String result,
            @RequestParam(value = "act", required = false) String act,
            @RequestParam(value = "groupId", required = false) String groupId, Model model) {
        if (result != null) {
            model.addAttribute("result", result);
        }
        if (act != null) {
            model.addAttribute("act", act);
        }
        if (groupId != null) {
            model.addAttribute("groupId", groupId);
        }
        return "group_edit";
    }

    @RequestMapping(value = "/groups/edit", method = RequestMethod.POST)
    public String processGroupEditPage(
            @RequestParam(value = "submit", required = true) String submit,
            @RequestParam(value = "groupName", required = false) String groupName,
            @RequestParam(value = "groupId", required = false) String groupId, Model model) {
        switch(submit){
            case "Add":
                if(groupName != null) {
                    Group group = null;
                    try {
                        group = new Group(-1, groupName);
                        boolean result = groupService.addGroup(group);
                        if (result) return "groups";
                    } catch (Exception e) {
                        logger.error("Error to add group", e);
                    }
                }
                model.addAttribute("result", "addErr");
                model.addAttribute("act", "add");
                break;
            case "Edit":
                if(groupName != null && groupId != null) {
                    try {
                        Group group = new Group(Integer.valueOf(groupId), groupName);
                        boolean result = groupService.updateGroup(group);
                        if (result) return "groups";
                    } catch (Exception e) {
                        logger.error("Error to edit group", e);
                    }
                }
                model.addAttribute("result", "editErr");
                model.addAttribute("act", "edit");
                model.addAttribute("group_id", groupId);
                break;
            case "Delete":
                if(groupId != null) {
                    try {
                        boolean result = groupService.deleteGroup(Integer.valueOf(groupId));
                        if (result) return "groups";
                    } catch (Exception e) {
                        logger.error("Error to delete group", e);
                    }
                }
                model.addAttribute("result", "delErr");
                model.addAttribute("act", "delete");
                model.addAttribute("group_id", groupId);
                break;
        }
        return "group_edit";
    }

}
