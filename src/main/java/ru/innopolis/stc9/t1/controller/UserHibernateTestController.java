package ru.innopolis.stc9.t1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.stc9.t1.entities.RoleH;
import ru.innopolis.stc9.t1.service.TestUserService;

import java.util.List;

@Controller
public class UserHibernateTestController {

    @Autowired
    private TestUserService testUserService;

    @RequestMapping("/tests/hibernateTest")
    public String getHibernateTest() {
        return "users/hibernateTest";
    }

    @RequestMapping("/tests/getAllUsersAndRoles")
    public String getAllUsersAndRoles(Model model) {
//        List<UserH> users = testUserService.getAllUsers();
        List<RoleH> roles = testUserService.getAllRoles();
//        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return "users/hibernateTestResult";
    }

    @RequestMapping("/tests/fillRoles")
    public String fillRoles(Model model) {
        testUserService.fillRoles();
        return getAllUsersAndRoles(model);
    }

    @RequestMapping("/tests/addIven")
    public String addIven(Model model) {
        testUserService.addIven();
        return getAllUsersAndRoles(model);
    }
}
