package ru.innopolis.stc9.t1.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.stc9.t1.db.dao.GroupDAOImplH;
import ru.innopolis.stc9.t1.pojo.Group;

import java.util.ArrayList;

@Service
@Transactional
public class GroupService {
    private final static Logger logger = Logger.getLogger(GroupService.class);

    @Autowired
    private GroupDAOImplH groupDAO;

    public GroupService() {}

    public Group getGroupById(int id){
        return groupDAO.getGroupById(id);
    }

    public Group getGroupByName(String name){
        return groupDAO.getGroupByName(name);
    }

    public ArrayList<Group> getAllGroups(){
        ArrayList<Group> arrayList = new ArrayList();
        arrayList.addAll(groupDAO.getAllGroups());
        return arrayList;
    }

    public boolean addGroup(Group group){
        return groupDAO.addGroup(group);
    }

    public boolean updateGroup(Group group){
        return groupDAO.updateGroup(group);
    }

    public boolean deleteGroup(int id) {
        return groupDAO.deleteGroup(id);
    }
}
