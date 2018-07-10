package ru.innopolis.stc9.t1.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc9.t1.db.dao.GroupDAOImpl;
import ru.innopolis.stc9.t1.pojo.Group;
import ru.innopolis.stc9.t1.service.GroupService;

@Controller
public class GroupEditController{
    private final static Logger logger = Logger.getLogger(GroupEditController.class);

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/groups/manager", method = RequestMethod.GET)
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
            model.addAttribute("group", groupService.getGroupById(Integer.valueOf(groupId)));
        }
        return "group_manager";
    }

    @RequestMapping(value = "/groups/delete", method = RequestMethod.POST)
    public String processDeleteGroup(
            @RequestParam(value = "groupId", required = false) String groupId, Model model) {

        if(groupId != null) {
            try {
                boolean result = groupService.deleteGroup(Integer.valueOf(groupId));
                if (result) {
                    model.addAttribute("arrayGroup", groupService.getAllGroups());
                    return "groups";
                }
            } catch (Exception e) {
                logger.error("Error to delete group", e);
            }
        }
        model.addAttribute("result", "delErr");
        return "error";
    }

    @RequestMapping(value = "/groups/add", method = RequestMethod.POST)
    public String processAddGroup(
            @RequestParam(value = "groupName", required = false) String groupName,
            Model model) {

        if(groupName != null) {
            Group group = null;
            try {
                group = new Group(-1, groupName);
                boolean result = groupService.addGroup(group);
                if (result){
                    model.addAttribute("arrayGroup", groupService.getAllGroups());
                    return "groups";
                }
            } catch (Exception e) {
                logger.error("Error to add group", e);
            }
        }
        model.addAttribute("result", "addErr");
        return "error";
    }

    @RequestMapping(value = "/groups/edit", method = RequestMethod.POST)
    public String processEditGroup(
            @RequestParam(value = "groupName", required = false) String groupName,
            @RequestParam(value = "groupId", required = false) String groupId, Model model) {

        if(groupName != null && groupId != null) {
            try {
                Group group = new Group(Integer.valueOf(groupId), groupName);
                boolean result = groupService.updateGroup(group);
                if (result){
                    model.addAttribute("arrayGroup", groupService.getAllGroups());
                    return "groups";
                }
            } catch (Exception e) {
                logger.error("Error to edit group", e);
            }
        }
        model.addAttribute("result", "editErr");
        return "error";
    }

}
