package ru.innopolis.stc9.t1.controller;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.t1.pojo.User;
import ru.innopolis.stc9.t1.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends HttpServlet {
    private static Logger logger = Logger.getLogger(LoginController.class);
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("logout".equals(action)) {
            req.getSession().invalidate();
        }
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setAttribute("message", "Введите логин и пароль");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String login = req.getParameter("userName");
        String password = req.getParameter("userPassword");
        if (userService.checkAuth(login, password)) {
            User user = userService.getUserByLogin(login);
            int userType = userService.getUserType(login);
            String userInfo = userService.getUserInfo(login);
            req.getSession().setAttribute("user", user);
            req.getSession().setAttribute("userInfo", userInfo);
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("userType", userType);
            logger.info("Login " + login + " , userType = " + userType);
            resp.sendRedirect(req.getContextPath() + "/user/dashboard");
        } else {
            resp.sendRedirect(req.getContextPath() + "/login?errorMsg=authErr");
        }
    }
}
