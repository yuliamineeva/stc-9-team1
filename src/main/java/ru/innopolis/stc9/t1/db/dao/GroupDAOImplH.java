package ru.innopolis.stc9.t1.db.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.t1.pojo.Group;

import java.util.List;

@Repository
public class GroupDAOImplH implements GroupDAO{
    private final static Logger logger = Logger.getLogger(GroupDAOImplH.class);

    @Autowired
    private SessionFactory sessionFactory;

    public GroupDAOImplH(){}

    public Group getGroupById(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Group where group_id = :param");
        query.setParameter("param", id);
        Group group = (Group) query.list().get(0);
        session.getTransaction().commit();
        return group;
    }

    public Group getGroupByName(String name) {
        if(name == null) return null;
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Group where name = :param");
        query.setParameter("param", name);
        if(query.list() == null || query.list().size() == 0) return null;
        Group group = (Group) query.list().get(0);
        session.getTransaction().commit();
        return group;
    }

    public List<Group> getAllGroups() {
        List<Group> groups = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Query query = session.createQuery("from Group");
            groups = query.list();
            session.getTransaction().commit();
        }catch(NullPointerException e){
            logger.error("error to get all groups", e);
        }
        return groups;
    }

    public boolean addGroup(Group group) {
        if(group == null) return false;
        Session session = sessionFactory.getCurrentSession();
        if(getGroupByName(group.getName()) != null) return false;
        session.beginTransaction();
        session.save(group);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public boolean updateGroup(Group group){
        if(group == null) return false;
        if(group.getGroup_id() < 1)return false;
        Group findGroup = getGroupByName(group.getName());
        if(findGroup != null && findGroup.getGroup_id() != group.getGroup_id()) return false;
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.merge(group);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public boolean deleteGroup(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Group group = (Group) session.load(Group.class, id);
        session.delete(group);
        session.getTransaction().commit();
        session.close();
        return true;
    }

}
