package ru.innopolis.stc9.t1.db.dao;

import ru.innopolis.stc9.t1.pojo.User;

import java.util.ArrayList;

public interface GroupListDAO {
    boolean deleteUserFromGroup(int group_id, int user_id);
    ArrayList<User> getGroupList(int group_id);
}
