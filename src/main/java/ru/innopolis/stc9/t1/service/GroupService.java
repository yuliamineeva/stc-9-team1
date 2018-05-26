package ru.innopolis.stc9.t1.service;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.t1.db.dao.GroupDAO;
import ru.innopolis.stc9.t1.db.dao.GroupDAOImpl;
import ru.innopolis.stc9.t1.pojo.Group;

import java.util.ArrayList;

public class GroupService {
    private final static Logger logger = Logger.getLogger(GroupService.class);

    GroupDAO groupDAO = new GroupDAOImpl();

    public Group getGrouptById(int id){
        return groupDAO.getGroupById(1);
    }

    public Group getGrouptByName(String name){
        return groupDAO.getGroupByName(name);
    }

    public ArrayList<Group> getAllGroups(){
        return groupDAO.getAllGroups();
    }

    public boolean addGroup(Group group){
        return groupDAO.addGroup(group);
    }

    public boolean deleteGroup(int id) {
        return groupDAO.deleteGroup(id);
    }
}
