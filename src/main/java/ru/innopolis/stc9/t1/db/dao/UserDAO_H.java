package ru.innopolis.stc9.t1.db.dao;

import ru.innopolis.stc9.t1.entities.RoleH;
import ru.innopolis.stc9.t1.entities.UserH;

import java.util.List;

public interface UserDAO_H {
    void addUser(UserH user);

    RoleH getRoleInt(int roleInt);

    UserH getUser(int userId);

    UserH getUserByLogin(String login);

    void updateUserNameByLogin(String login, String name);

    void updateUserPasswordByLogin(String password, String name);

    List<UserH> getAllUsers();

    List<UserH> getAllUsersByType(int type);

    void updateUserRole(int userId, int newRoleInt);

    void deleteUser(int userId);
}
