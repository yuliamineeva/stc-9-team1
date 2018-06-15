package ru.innopolis.stc9.t1.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc9.t1.ErrorMsgHandler;
import ru.innopolis.stc9.t1.pojo.User;
import ru.innopolis.stc9.t1.service.UserService;

import java.util.List;

/**
 * registration, login, edit users
 */

@Controller
public class UserController {

    private final static Logger logger = Logger.getLogger(GroupEditController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(
            @RequestParam(value = "login_error", required = false) String login_error,
            @RequestParam(value = "logout", required = false) String logout, Model model) {
        if (login_error != null) {
            model.addAttribute("login_error", "Invalid username or/and password!");
        }
        if (logout != null) {
            model.addAttribute("msg", "You've been logged out successfully.");
        }
        return "login";
    }

    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public String setRegistration(@RequestParam String userLogin, String userName, String userPassword, Model model) {
        userLogin = userLogin.trim();
        userName = userName.trim();
        userPassword = userPassword.trim();
        String reply = null;
        String err = null;
        if (userLogin.isEmpty() || userName.isEmpty() || userPassword.isEmpty()) {
            reply = "заполните все поля";
        } else {
            userService.addStudent(userLogin, userPassword, userName);
            err = ErrorMsgHandler.getMessage();
            if (err == null) {
                reply = "user " + userLogin + " has been registered, you can login";
            }
        }
        model.addAttribute("reply", reply);
        model.addAttribute("err", err);
        return "registration";
    }

    @RequestMapping(value = "**/profile", method = RequestMethod.GET)
    public String getProfile(@RequestParam(value = "editProfile", required = false) String editProfile,
                             @RequestParam(value = "errorProfile", required = false) String errorProfile,
                             Model model) {
        return "users/profile";
    }

    @RequestMapping("admin/userlist")
    public String getUserList(Model model) {
        List<User> users = userService.getAllUsers();
        String err = ErrorMsgHandler.getMessage();
        model.addAttribute("err", err);
        model.addAttribute("users", users);
        return "users/userlist";
    }
}
