package ru.innopolis.stc9.t1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.t1.ErrorMsgHandler;
import ru.innopolis.stc9.t1.db.connection.CryptoUtils;
import ru.innopolis.stc9.t1.db.dao.UserDAO_H;
import ru.innopolis.stc9.t1.entities.RoleH;
import ru.innopolis.stc9.t1.entities.UserH;

import java.util.List;

@Service
public class UserServiceH {
    @Autowired
    private UserDAO_H userDAO;

    public void addUser(String login, String pass, String name) {
        try {
            String HashPassword = CryptoUtils.computeHashPassword(pass);
            UserH user = new UserH(login, HashPassword, name);
            RoleH role = userDAO.getRoleInt(2);
            user.setRole(role);
            userDAO.addUser(user);
        } catch (Exception e) {
            ErrorMsgHandler.setMessage("add User", e);
        }
    }

    public UserH getUser(int userId) {
        UserH user = null;
        try {
            user = userDAO.getUser(userId);
        } catch (Exception e) {
            ErrorMsgHandler.setMessage("get User by id", e);
        }
        return user;
    }

    public UserH getUserByLogin(String login) {
        UserH user = null;
        try {
            user = userDAO.getUserByLogin(login);
        } catch (Exception e) {
            ErrorMsgHandler.setMessage("get User by login", e);
        }
        return user;
    }

    public void updateUserNameByLogin(String login, String name) {
        try {
            userDAO.updateUserNameByLogin(login, name);
        } catch (Exception e) {
            ErrorMsgHandler.setMessage("update UserName by login", e);
        }
    }

    public void updateUserPasswordByLogin(String login, String password) {
        try {
            userDAO.updateUserPasswordByLogin(login, password);
        } catch (Exception e) {
            ErrorMsgHandler.setMessage("update password by login", e);
        }
    }

    public List<UserH> getAllUsers() {
        List<UserH> users = null;
        try {
            users = userDAO.getAllUsers();
        } catch (Exception e) {
            ErrorMsgHandler.setMessage("get all users", e);
        }
        return users;
    }

    public void updateUserRole(int userId, int newRoleInt) {
        try {
            userDAO.updateUserRole(userId, newRoleInt);
        } catch (Exception e) {
            ErrorMsgHandler.setMessage("update role", e);
        }
    }

    public void deleteUser(int userId) {
        try {
            userDAO.deleteUser(userId);
        } catch (Exception e) {
            ErrorMsgHandler.setMessage("delete user", e);
        }
    }
}