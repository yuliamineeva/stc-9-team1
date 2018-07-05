package ru.innopolis.stc9.t1.db.dao;

import org.hibernate.HibernateException;
import ru.innopolis.stc9.t1.entities.RoleH;
import ru.innopolis.stc9.t1.entities.UserH;

import java.util.List;

public interface UserDAO_H {
    void addUser(UserH user) throws HibernateException;

    RoleH getRoleInt(int roleInt) throws HibernateException;

    UserH getUser(int userId) throws HibernateException;

    UserH getUserByLogin(String login) throws HibernateException;

    void updateUserNameByLogin(String login, String name) throws HibernateException;

    void updateUserPasswordByLogin(String password, String name) throws HibernateException;

    List<UserH> getAllUsers() throws HibernateException;

    List<UserH> getAllUsersByType(int type);

    void updateUserRole(int userId, int newRoleInt) throws HibernateException;

    void deleteUser(int userId) throws HibernateException;
}
