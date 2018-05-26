package ru.innopolis.stc9.t1.db.dao;

import ru.innopolis.stc9.t1.pojo.Group;

import java.util.ArrayList;

public interface GroupDAO {

    public Group getGroupById(int id);

    public ArrayList<Group> getAllGroup();

}

