package ru.innopolis.stc9.t1.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc9.t1.db.dao.GroupListDAOImpl;
import ru.innopolis.stc9.t1.service.GroupListService;

@Controller
public class GroupListEditController {
    private final static Logger logger = Logger.getLogger(GroupListEditController.class);
    private GroupListService groupListService = new GroupListService(new GroupListDAOImpl());

    @RequestMapping(value = "/group_list/manager", method = RequestMethod.GET)
    public String getGroupEditPage(
            @RequestParam(value = "result", required = false) String result,
            @RequestParam(value = "act", required = false) String act,
            @RequestParam(value = "groupId", required = false) String groupId,
            @RequestParam(value = "userId", required = false) String userId, Model model) {
        if (result != null) {
            model.addAttribute("result", result);
        }
        if (act != null) {
            model.addAttribute("act", act);
        }
        if (groupId != null) {
            model.addAttribute("groupId", groupId);
        }
        if (userId != null) {
            model.addAttribute("userId", userId);
        }
        return "group_list_manager";
    }

    @RequestMapping(value = "/group_list/delete", method = RequestMethod.POST)
    public String processDeleteGroup(
            @RequestParam(value = "groupId", required = false) String groupId,
            @RequestParam(value = "userId", required = false) String userId, Model model) {

        if(groupId != null && userId != null) {
            try {
                boolean result = groupListService.deleteUserFromGroup(Integer.valueOf(groupId), Integer.valueOf(userId));
                if (result) return "group_list";
            } catch (Exception e) {
                logger.error("Error to delete group", e);
            }
        }
        model.addAttribute("result", "delErr");
        model.addAttribute("act", "delete");
        model.addAttribute("groupId", groupId);
        model.addAttribute("userId", userId);
        return "group_list_manager";
    }

}
