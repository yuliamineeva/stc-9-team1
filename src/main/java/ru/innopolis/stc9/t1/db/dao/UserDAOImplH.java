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
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public RoleH getRoleInt(int roleInt) throws HibernateException {
        Session session = factory.openSession();
        Query query = session.createQuery("FROM RoleH WHERE role_int = ?");
        query.setParameter(0, roleInt);
        List<RoleH> roles = query.list();
        session.close();
        return roles.size() == 0 ? null : roles.get(0);
    }

    @Override
    public UserH getUser(int userId) throws HibernateException {
        Session session = factory.openSession();
        session.beginTransaction();
        UserH user = session.get(UserH.class, userId);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    @Override
    public UserH getUserByLogin(String login) throws HibernateException {
        Session session = factory.openSession();
        Query query = session.createQuery("FROM UserH WHERE login = ?");
        query.setParameter(0, login);
        List<UserH> users = query.list();
        session.close();
        return users.size() == 0 ? null : users.get(0);
    }

    @Override
    public void updateUserNameByLogin(String login, String name) throws HibernateException {
        UserH user = getUserByLogin(login);
        user.setName(name);
        Session session = factory.openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateUserPasswordByLogin(String login, String newHashPassword) throws HibernateException {
        UserH user = getUserByLogin(login);
        user.setPassword(newHashPassword);
        Session session = factory.openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<UserH> getAllUsers() throws HibernateException {
        Session session = factory.openSession();
        Query query = session.createQuery("FROM UserH");
        List<UserH> users = query.list();
        session.close();
        return users;
    }

    @Override
    public List<UserH> getAllUsersByType(int type) {
        Session session = factory.openSession();
        Query query = session.createQuery("from UserH where role.role_int = ?");
        query.setParameter(0, type);
        List<UserH> users = query.list();
        session.close();
        return users;
    }

    @Override
    public void updateUserRole(int userId, int newRoleInt) throws HibernateException {
        UserH user = getUser(userId);
        RoleH role = getRoleInt(newRoleInt);
        user.setRole(role);
        Session session = factory.openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteUser(int userId) throws HibernateException {
        UserH user = getUser(userId);
        Session session = factory.openSession();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

}
