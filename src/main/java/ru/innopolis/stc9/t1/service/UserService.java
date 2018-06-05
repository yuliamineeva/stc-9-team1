package ru.innopolis.stc9.t1.service;

import org.springframework.stereotype.Service;
import ru.innopolis.stc9.t1.db.connection.CryptoUtils;
import ru.innopolis.stc9.t1.db.dao.UserDAO;
import ru.innopolis.stc9.t1.db.dao.UserDAOImpl;
import ru.innopolis.stc9.t1.pojo.User;

import java.sql.SQLException;

@Service
public class UserService {
    private static UserDAO userDao = new UserDAOImpl();

    public int addStudent(String login, String pass, String name) throws SQLException {
        return userDao.addStudent(login, CryptoUtils.computeHashPassword(pass), name);
    }

    public User getUserByLogin(String login) {
        User user = userDao.getUserByLogin(login);
        return user;
    }

    public boolean checkAuth(String login, String password) {
        User user = userDao.getUserByLogin(login);
        String passwordFromBD = user.getPassword();
        String hashPassword = CryptoUtils.computeHashPassword(password);
        return (user != null) && (passwordFromBD.equals(hashPassword));
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
}
