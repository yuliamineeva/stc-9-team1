package ru.innopolis.stc9.t1.db.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.t1.entities.RoleH;
import ru.innopolis.stc9.t1.entities.UserH;

import java.util.List;

@Repository
public class TestUserDAOImpl implements TestUserDAO {
    @Autowired
    private SessionFactory factory;

    @Override
    public void addRole(RoleH role) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(role);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<RoleH> getAllRoles() {
        /*Session session = factory.openSession();
        Query query = session.createQuery("FROM RoleH");
        List<RoleH> roles = query.list();*/
        List<RoleH> roles = factory.getCurrentSession().createQuery("FROM RoleH").list();
        return roles;
    }

    @Override
    public RoleH getRoleInt(int roleInt) {
        RoleH role;
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("FROM RoleH WHERE role_int = ?"); //:roleInt
            query.setParameter(0, roleInt);
            role = (RoleH) query.list().get(0);
        }
        return role;
    }

    @Override
    public void addUser(UserH user) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<UserH> getAllUsers() {
        //List<UserH> users;
        Session session = factory.openSession();
        Query query = session.createQuery("FROM UserH");
        List<UserH> users = query.list();
        session.close();
        return users;
    }
}
