package ru.innopolis.stc9.t1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.stc9.t1.db.connection.CryptoUtils;
import ru.innopolis.stc9.t1.db.dao.TestUserDAO;
import ru.innopolis.stc9.t1.entities.RoleH;
import ru.innopolis.stc9.t1.entities.UserH;

import java.util.List;

@Service
@Transactional
public class TestUserService {
    @Autowired
    private TestUserDAO testUserDAO;

    public void fillRoles() {
        RoleH role = new RoleH(1, "ROLE_TEACHER", "преподаватель");
        testUserDAO.addRole(role);
        role = new RoleH(2, "ROLE_USER", "студент");
        testUserDAO.addRole(role);
        role = new RoleH(3, "ROLE_ADMIN", "админ");
        testUserDAO.addRole(role);
    }

    public List<RoleH> getAllRoles() {
        return testUserDAO.getAllRoles();
    }

    public void addIven() {
        UserH user = new UserH("iven", CryptoUtils.computeHashPassword("qwerty"), "Iven Ivens");
        RoleH role = testUserDAO.getRoleInt(2);
        user.setRole(role);
        testUserDAO.addUser(user);
    }

    public List<UserH> getAllUsers() {
        return testUserDAO.getAllUsers();
    }
}
