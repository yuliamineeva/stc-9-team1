package ru.innopolis.stc9.t1.db.dao;

import ru.innopolis.stc9.t1.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    int addStudent(String login, String hashPass, String name) throws SQLException;

    boolean addUser(User user);

    User getUserById(int id);

    User getUserByLogin(String login);

    List<User> getAllUsersByType(int type);

    List<User> getAllUsers();

    List<User> getAllUsersNotInGroup(int group_id);

    boolean updateUser(User user);

    boolean deleteUserById(int id);
}
