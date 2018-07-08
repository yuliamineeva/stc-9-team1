package ru.innopolis.stc9.t1.db.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.t1.entities.RoleH;
import ru.innopolis.stc9.t1.entities.UserH;

import java.util.List;

@Repository
public class UserDAOImplH implements UserDAO_H {
    @Autowired
    private SessionFactory factory;

    @Override
    public void addUser(UserH user) throws HibernateException {
        factory.getCurrentSession().save(user);
    }

    @Override
    public RoleH getRoleInt(int roleInt) throws HibernateException {
        List roles = factory.getCurrentSession().
                createQuery("FROM RoleH WHERE role_int = ?").
                setParameter(0, roleInt).
                list();
        return roles.size() == 0 ? null : (RoleH) roles.get(0);
    }

    @Override
    public UserH getUser(int userId) throws HibernateException {
        return factory.getCurrentSession().get(UserH.class, userId);
    }

    @Override
    public UserH getUserByLogin(String login) throws HibernateException {
        List users = factory.getCurrentSession().
                createQuery("FROM UserH WHERE login = ?").
                setParameter(0, login).
                list();
        return users.size() == 0 ? null : (UserH) users.get(0);
    }

    @Override
    public void updateUserNameByLogin(String login, String name) throws HibernateException {
        UserH user = getUserByLogin(login);
        user.setName(name);
        factory.getCurrentSession().update(user);
    }

    @Override
    public void updateUserPasswordByLogin(String login, String newHashPassword) throws HibernateException {
        UserH user = getUserByLogin(login);
        user.setPassword(newHashPassword);
        factory.getCurrentSession().update(user);
    }

    @Override
    public List<UserH> getAllUsers() throws HibernateException {
        @SuppressWarnings("unchecked")
        List<UserH> users = factory.getCurrentSession().createQuery("FROM UserH").list();
        return users;
    }

    @Override
    public List<UserH> getAllUsersByType(int type) {
        Session session = factory.openSession();
        Query query = session.createQuery("from UserH where role.role_int = ?");
        query.setParameter(0, type);
        @SuppressWarnings("unchecked")
        List<UserH> users = query.list();
        session.close();
        return users;
    }

    @Override
    public void updateUserRole(int userId, int newRoleInt) throws HibernateException {
        UserH user = getUser(userId);
        RoleH role = getRoleInt(newRoleInt);
        user.setRole(role);
        factory.getCurrentSession().update(user);
    }

    @Override
    public void deleteUser(int userId) throws HibernateException {
        UserH user = getUser(userId);
        factory.getCurrentSession().delete(user);
    }

}
