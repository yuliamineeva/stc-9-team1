package ru.innopolis.stc9.t1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.stc9.t1.ErrorMsgHandler;
import ru.innopolis.stc9.t1.db.connection.CryptoUtils;
import ru.innopolis.stc9.t1.db.dao.UserDAO_H;
import ru.innopolis.stc9.t1.entities.RoleH;
import ru.innopolis.stc9.t1.entities.UserH;

import java.util.List;

@Service
@Transactional
public class UserServiceH {
    @Autowired
    private UserDAO_H userDAO;

    public void addUser(String login, String pass, String name) {
        try {
            String hashPassword = CryptoUtils.computeHashPassword(pass);
            UserH user = new UserH(login, hashPassword, name);
            RoleH role = userDAO.getRoleInt(2);
            user.setRole(role);
            userDAO.addUser(user);
        } catch (Exception e) {
            ErrorMsgHandler.setMessage("error while adding user", e);
        }
    }

    public UserH getUser(int userId) {
        UserH user = null;
        try {
            user = userDAO.getUser(userId);
        } catch (Exception e) {
            ErrorMsgHandler.setMessage("error while getting User by id", e);
        }
        return user;
    }

    public UserH getUserByLogin(String login) {
        UserH user = null;
        try {
            user = userDAO.getUserByLogin(login);
        } catch (Exception e) {
            ErrorMsgHandler.setMessage("error while getting User by login", e);
        }
        return user;
    }

    public void updateUserNameByLogin(String login, String name) {
        try {
            userDAO.updateUserNameByLogin(login, name);
        } catch (Exception e) {
            ErrorMsgHandler.setMessage("error while updating UserName by login", e);
        }
    }

    public void updateUserPasswordByLogin(String login, String newPassword) {
        try {
            String hashPassword = CryptoUtils.computeHashPassword(newPassword);
            userDAO.updateUserPasswordByLogin(login, hashPassword);
        } catch (Exception e) {
            ErrorMsgHandler.setMessage("error while updating password by login", e);
        }
    }

    public List<UserH> getAllUsers() {
        List<UserH> users = null;
        try {
            users = userDAO.getAllUsers();
        } catch (Exception e) {
            ErrorMsgHandler.setMessage("error while getting all users", e);
        }
        return users;
    }


    public List<UserH> getAllUsersByType(int type) {
        List<UserH> users = userDAO.getAllUsersByType(type);
        return users;
    }

    public void updateUserRole(int userId, int newRoleInt) {
        try {
            userDAO.updateUserRole(userId, newRoleInt);
        } catch (Exception e) {
            ErrorMsgHandler.setMessage("error while updating role", e);
        }
    }

    public void deleteUser(int userId) {
        try {
            userDAO.deleteUser(userId);
        } catch (Exception e) {
            ErrorMsgHandler.setMessage("error while deleting user", e);
        }
    }
}