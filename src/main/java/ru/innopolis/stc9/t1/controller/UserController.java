package ru.innopolis.stc9.t1.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc9.t1.ErrorMsgHandler;
import ru.innopolis.stc9.t1.entities.UserH;
import ru.innopolis.stc9.t1.service.UserServiceH;

import java.util.List;

/**
 * registration, login, edit users
 */

@Controller
public class UserController {

    private final static Logger logger = Logger.getLogger(GroupEditController.class);
    @Autowired
    private UserServiceH userService;

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
            userService.addUser(userLogin, userPassword, userName);
            err = ErrorMsgHandler.getMessage();
            if (err == null) {
                reply = "user " + userLogin + " has been registered, you can login";
            }
        }
        model.addAttribute("reply", reply);
        model.addAttribute("err", err);
        return "registration";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getProfile(Model model) {
        String currentUserLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        UserH currentUser = userService.getUserByLogin(currentUserLogin);
        String errorGetUser = ErrorMsgHandler.getMessage();
        model.addAttribute("errorGetUser", errorGetUser);
        model.addAttribute("user", currentUser);
        return "users/profile";
    }

    @RequestMapping(value = "/profileChangeName", method = RequestMethod.POST)
    public String setProfileChangeName(@RequestParam String newName, Model model) {
        String currentUserLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.updateUserNameByLogin(currentUserLogin, newName);
        String errorEditUser = ErrorMsgHandler.getMessage();
        boolean nameChanged = (errorEditUser == null);
        model.addAttribute("errorEditUser", errorEditUser);
        model.addAttribute("nameChanged", nameChanged);
        return getProfile(model);
    }

    @RequestMapping(value = "/profileChangePass", method = RequestMethod.POST)
    public String setProfileChangePass(@RequestParam String newPass, Model model) {
        String currentUserLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.updateUserPasswordByLogin(currentUserLogin, newPass);
        String errorEditUser = ErrorMsgHandler.getMessage();
        if (errorEditUser != null) {
            model.addAttribute("errorEditUser", errorEditUser);
            return getProfile(model);
        } else {
            SecurityContextHolder.clearContext();
            model.addAttribute("passChanged", true);
            return "login";
        }
    }

    @RequestMapping("admin/userList")
    public String getUserList(Model model) {
        List<UserH> users = userService.getAllUsers();
        String errorGetUserList = ErrorMsgHandler.getMessage();
        model.addAttribute("errorGetUserList", errorGetUserList);
        model.addAttribute("users", users);
        return "users/userList";
    }

    @RequestMapping(value = "/admin/userList/editRole", method = RequestMethod.GET)
    public String getEditRole(@RequestParam int id, Model model) {
        UserH editableUser = userService.getUser(id);
        String errorGetUser = ErrorMsgHandler.getMessage();
        String currentUserLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean himself = currentUserLogin.equals(editableUser.getLogin());
        model.addAttribute("errorGetUser", errorGetUser);
        model.addAttribute("himself", himself);
        model.addAttribute("user", editableUser);
        return "users/userEditRole";
    }

    @RequestMapping(value = "/admin/userList/editRole", method = RequestMethod.POST)
    public String setEditRole(@RequestParam int userId, String userLogin, int newRoleId, Model model) {
        userService.updateUserRole(userId, newRoleId);
        String errorEditUser = ErrorMsgHandler.getMessage();
        model.addAttribute("editedUserLogin", userLogin);
        model.addAttribute("errorEditUser", errorEditUser);
        return getUserList(model);
    }

    @RequestMapping(value = "/admin/userList/deleteUser", method = RequestMethod.GET)
    public String getDeleteUser(@RequestParam int id, Model model) {
        UserH deletedUser = userService.getUser(id);
        String errorGetUser = ErrorMsgHandler.getMessage();
        String currentUserLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean himself = currentUserLogin.equals(deletedUser.getLogin());
        model.addAttribute("errorGetUser", errorGetUser);
        model.addAttribute("himself", himself);
        model.addAttribute("user", deletedUser);
        return "users/userDelete";
    }

    @RequestMapping(value = "/admin/userList/deleteUser", method = RequestMethod.POST)
    public String setDeleteUser(@RequestParam int userId, String userLogin, Model model) {
        userService.deleteUser(userId);
        String errorEditUser = ErrorMsgHandler.getMessage();
        model.addAttribute("deletedUserLogin", userLogin);
        model.addAttribute("errorEditUser", errorEditUser);
        return getUserList(model);
    }
}