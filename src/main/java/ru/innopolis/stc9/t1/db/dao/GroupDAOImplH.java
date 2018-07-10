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
    private SessionFactory sessionFactory;

    @Autowired
    public GroupDAOImplH(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Group getGroupById(int id) {
        Group group = sessionFactory.getCurrentSession().get(Group.class, id);
        return group;
    }

    @Override
    public Group getGroupByName(String name) {
        if(name == null) return null;
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Group where name = :param");
        query.setParameter("param", name);
        if(query.list() == null || query.list().size() == 0) return null;
        Group group = (Group) query.list().get(0);
        return group;
    }

    @Override
    public List<Group> getAllGroups() {
        List<Group> groups = sessionFactory.getCurrentSession().createQuery("from Group").list();
        return groups;
    }

    @Override
    public boolean addGroup(Group group) {
        if(group == null) return false;
        Session session = sessionFactory.getCurrentSession();
        if(getGroupByName(group.getName()) != null) return false;
        session.save(group);
        return true;
    }

    @Override
    public boolean updateGroup(Group group){
        if(group == null) return false;
        Group findGroup = getGroupByName(group.getName());
        if(findGroup != null && findGroup.getGroup_id() != group.getGroup_id()) return false;
        Session session = sessionFactory.getCurrentSession();
        session.merge(group);
        return true;
    }

    @Override
    public boolean deleteGroup(int id) {
        Session session = sessionFactory.getCurrentSession();
        Group group = (Group) session.load(Group.class, id);
        session.delete(group);
        return true;
    }

}
