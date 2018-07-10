package ru.innopolis.stc9.t1.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc9.t1.db.dao.GroupListDAOImpl;
import ru.innopolis.stc9.t1.entities.UserH;
import ru.innopolis.stc9.t1.pojo.Group;
import ru.innopolis.stc9.t1.service.GroupListService;
import ru.innopolis.stc9.t1.service.GroupService;
import ru.innopolis.stc9.t1.service.UserServiceH;

import java.util.List;
import java.util.Set;

@Controller
public class GroupListEditController {
    private final static Logger logger = Logger.getLogger(GroupListEditController.class);

    @Autowired
    private GroupService groupService;
    @Autowired
    private UserServiceH userService;

    @RequestMapping(value = "/group_list/manager", method = RequestMethod.GET)
    public String getGroupListEditPage(
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
            model.addAttribute("group", groupService.getGroupById(Integer.valueOf(groupId)));
            if(act.equals("add")){
                List<UserH> usersNotGroup = userService.getAllUsersNotInGroup(Integer.valueOf(groupId));
                model.addAttribute("usersNotGroup", usersNotGroup);
            }
        }
        if (userId != null) {
            model.addAttribute("user", userService.getUser(Integer.valueOf(userId)));
        }
        return "group_list_manager";
    }

    @RequestMapping(value = "/group_list/delete", method = RequestMethod.POST)
    public String deleteUserFromGroup(
            @RequestParam(value = "groupId", required = false) String groupId,
            @RequestParam(value = "userId", required = false) String userId, Model model) {

        if(groupId != null && userId != null) {
            try {
                Group group = groupService.getGroupById(Integer.valueOf(groupId));
                Set<UserH> users = group.getUsers();
                for(UserH user: users){
                    if(user.getId() == Integer.valueOf(userId)){
                        if(users.remove(user)){
                            group.setUsers(users);
                            groupService.updateGroup(group);
                            model.addAttribute("group", group);
                            model.addAttribute("groupList", group.getUsers());
                            return "group_list";
                        }
                        break;
                    }
                }
            } catch (Exception e) {
                logger.error("Error to delete group", e);
            }
        }
        model.addAttribute("result", "delErr");
        return "error";
    }

    @RequestMapping(value = "/group_list/add", method = RequestMethod.GET)
    public String addUserToGroup(
            @RequestParam(value = "groupId", required = false) String groupId,
            @RequestParam(value = "userId", required = false) String userId, Model model) {

        if(groupId != null && userId != null) {
            try {
                Group group = groupService.getGroupById(Integer.valueOf(groupId));
                UserH newUser = userService.getUser(Integer.valueOf(userId));
                group.addUser(newUser);
                groupService.updateGroup(group);
                model.addAttribute("group", group);
                model.addAttribute("groupList", group.getUsers());
                return "group_list";
            } catch (Exception e) {
                logger.error("Error to add user in group", e);
            }
        }
        model.addAttribute("result", "addErr");
        return "error";
    }

}
