package ru.innopolis.stc9.t1.db.dao;

import ru.innopolis.stc9.t1.pojo.UserType;

import java.util.List;

public interface UserTypeDAO {

    boolean addUserType(UserType userType);

    UserType getUserTypeById(int id);

    List<UserType> getAllUserTypes();

    boolean updateUserType(UserType userType);

    boolean deleteUserTypeById(int id);
}
