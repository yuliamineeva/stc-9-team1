package ru.innopolis.stc9.t1.db.dao;

import ru.innopolis.stc9.t1.entities.RoleH;
import ru.innopolis.stc9.t1.entities.UserH;

import java.util.List;

public interface TestUserDAO {
    void addRole(RoleH role);

    List<RoleH> getAllRoles();

    RoleH getRoleInt(int roleInt);

    void addUser(UserH user);

    List<UserH> getAllUsers();
}
