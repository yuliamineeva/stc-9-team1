package ru.innopolis.stc9.t1.db.dao;

import ru.innopolis.stc9.t1.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    // insert
    int addStudent(String login, String hashPass, String name) throws SQLException;

    boolean addUser(User user);

    // select
    User getUserById(int id);

    User getUserByLogin(String login);

    List<User> getAllUsersByType(int type);

    List<User> getAllUsers() throws SQLException;

    List<User> getAllUsersNotInGroup(int group_id);

    // delete
    int deleteUserById(int id) throws SQLException;

    // update
    boolean updateUser(User user);

    int updateUserRole(int id, int roleId) throws SQLException;

    int updateUserNameByLogin(String login, String name) throws SQLException;

    int updateUserPasswordByLogin(String login, String hashPassword) throws SQLException;
}
