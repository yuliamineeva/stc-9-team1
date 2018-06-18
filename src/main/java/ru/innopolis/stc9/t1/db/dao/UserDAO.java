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

    List<User> getAllUsers() throws SQLException;

    List<User> getAllUsersNotInGroup(int group_id);

    boolean updateUser(User user);

    int deleteUserById(int id) throws SQLException;

    int updateUserRole(int id, int roleId) throws SQLException;
}
