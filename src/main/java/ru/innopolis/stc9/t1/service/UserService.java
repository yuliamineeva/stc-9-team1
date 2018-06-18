package ru.innopolis.stc9.t1.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.t1.ErrorMsgHandler;
import ru.innopolis.stc9.t1.db.connection.CryptoUtils;
import ru.innopolis.stc9.t1.db.dao.UserDAO;
import ru.innopolis.stc9.t1.db.dao.UserDAOImpl;
import ru.innopolis.stc9.t1.pojo.User;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {
    private final static Logger logger = Logger.getLogger(UserService.class);
    private UserDAO userDao;

    public UserService() {
        userDao = new UserDAOImpl();
    }

    public UserService(UserDAO userDao) {
        this.userDao = userDao;
    }

    public boolean checkAuth(String login, String password) {
        User user = userDao.getUserByLogin(login);
        String passwordFromBD = null;
        if (user != null) {
            passwordFromBD = user.getPassword();
        }
        String hashPassword = CryptoUtils.computeHashPassword(password);
        return (user != null) && (passwordFromBD.equals(hashPassword));
    }

    // insert ---------
    public void addStudent(String login, String pass, String name) {
        try {
            int addRowCount = userDao.addStudent(login, CryptoUtils.computeHashPassword(pass), name);
            if (addRowCount != 1) {
                ErrorMsgHandler.setMessage("error: added " + addRowCount + " rows");
            }
        } catch (SQLException e) {
            ErrorMsgHandler.setMessage("Error trying to add Student to DB", e);
        }
    }

    // select -----------
    public User getUserByLogin(String login) {
        User user = userDao.getUserByLogin(login);
        return user;
    }

    public int getUserType(String login) {
        User user = userDao.getUserByLogin(login);
        return (user != null) ? user.getType_id() : 0;
    }

    public String getUserInfo(String login) {
        User user = getUserByLogin(login);
        String userInfo = "";
        if (user != null) {
            userInfo = user.toString();
        }
        return userInfo;
    }

    public List<User> getAllUsersNotInGroup(int group_id) {
        List<User> users = userDao.getAllUsersNotInGroup(group_id);
        return users;
    }

    public List<User> getAllUsers() {
        List<User> users = null;
        try {
            users = userDao.getAllUsers();
        } catch (SQLException e) {
            ErrorMsgHandler.setMessage("Error trying to get all users from DB", e);
        }
        return users;
    }

    // delete ----------
    public void deleteUserById(int id) {
        try {
            int deleteRowCount = userDao.deleteUserById(id);
            if (deleteRowCount != 1) {
                ErrorMsgHandler.setMessage("DB error, deleting user, delete " + deleteRowCount + " rows");
            }
        } catch (SQLException e) {
            ErrorMsgHandler.setMessage("DB error, deleting user", e);
        }
    }

    // update ---------
    public void updateUserRole(int id, int roleId) {
        try {
            int updateRowCount = userDao.updateUserRole(id, roleId);
            if (updateRowCount != 1) {
                ErrorMsgHandler.setMessage("DB error, updating user role, update " + updateRowCount + " rows");
            }
        } catch (SQLException e) {
            ErrorMsgHandler.setMessage("DB error, updating user role", e);
        }
    }

    public void updateUserNameByLogin(String login, String name) {
        try {
            int updateRowCount = userDao.updateUserNameByLogin(login, name);
            if (updateRowCount != 1) {
                ErrorMsgHandler.setMessage("DB error, updating user name, update " + updateRowCount + " rows");
            }
        } catch (SQLException e) {
            ErrorMsgHandler.setMessage("DB error, updating user name", e);
        }
    }

    public void updateUserPasswordByLogin(String login, String password) {
        String hashPassword = CryptoUtils.computeHashPassword(password);
        try {
            int updateRowCount = userDao.updateUserPasswordByLogin(login, hashPassword);
            if (updateRowCount != 1) {
                ErrorMsgHandler.setMessage("DB error, updating user password, update " + updateRowCount + " rows");
            }
        } catch (SQLException e) {
            ErrorMsgHandler.setMessage("DB error, updating user password", e);
        }
    }


}
