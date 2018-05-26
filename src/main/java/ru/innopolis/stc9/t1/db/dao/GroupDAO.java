package ru.innopolis.stc9.t1.db.dao;

import ru.innopolis.stc9.t1.pojo.Group;

import java.util.ArrayList;

public interface GroupDAO {

    public Group getGroupById(int id);

    public Group getGroupByName(String name);

    public ArrayList<Group> getAllGroups();

    public boolean addGroup(Group group);

    public boolean deleteGroup(int id);

}

