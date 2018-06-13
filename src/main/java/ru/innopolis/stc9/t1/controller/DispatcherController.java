package ru.innopolis.stc9.t1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc9.t1.service.UserService;

@Controller
public class DispatcherController {
    @Autowired
    private UserService userService;

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

    @RequestMapping("/registr")
    public String getRegistrPage() {
        return "registr";
    }

    private String setRegistrReply(String login, String name, String password) {
        String result;
        if (login.isEmpty()) {
            result = "login is empty";
        } else if (name.isEmpty()) {
            result = "name is empty";
        } else if (password.isEmpty()) {
            result = "password is empty";
        } else {
                int addRowCount = userService.addStudent(login, password, name);
                if (addRowCount == 1) {
                    result = "user " + login + " has been registered, you can login";
                } else {
                    result = "error: added " + addRowCount + " rows";
                }
        }
        return result;
    }

    @RequestMapping(path = "/registr", method = RequestMethod.POST)
    public String setRegistration(@RequestParam String userLogin, String userName, String userPassword, Model model) {
        model.addAttribute("RegistrReply", setRegistrReply(userLogin.trim(), userName.trim(), userPassword.trim()));
        return "registr";
    }

    @RequestMapping(value = "**/profile", method = RequestMethod.GET)
    public String getProfilePage(@RequestParam(value = "editProfile", required = false) String editProfile,
                                 @RequestParam(value = "errorProfile", required = false) String errorProfile,
                                 Model model) {
        return "profile";
    }

    @RequestMapping("/userlist")
    public String getUserListPage() {
        return "userlist";
    }

}
