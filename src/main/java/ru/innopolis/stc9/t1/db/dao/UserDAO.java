package ru.innopolis.stc9.t1.db.dao;

import ru.innopolis.stc9.t1.pojo.User;

import java.util.List;

public interface UserDAO {

    boolean addUser(User user);

    User getUserById(int id);

    User getUserByLogin(String login);

    List<User> getAllUsersByType(int type);

    List<User> getAllUsers();

    boolean updateUser(User user);

    boolean deleteUserById(int id);
}
