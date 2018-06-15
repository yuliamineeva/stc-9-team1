package ru.innopolis.stc9.t1.service;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.t1.db.dao.GroupListDAO;
import ru.innopolis.stc9.t1.pojo.User;

import java.util.ArrayList;

public class GroupListService {
    private final static Logger logger = Logger.getLogger(GroupListService.class);
    private GroupListDAO groupListDAO;

    public GroupListService(GroupListDAO groupListDAO){
        this.groupListDAO = groupListDAO;
    }

    public boolean deleteUserFromGroup(int group_id, int user_id){
        return groupListDAO.deleteUserFromGroup(group_id, user_id);
    }

    public ArrayList<User> getGroupList(int group_id){
        return groupListDAO.getGroupList(group_id);
    }
}
