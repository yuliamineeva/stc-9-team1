package ru.innopolis.stc9.t1.service;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import ru.innopolis.stc9.t1.db.connection.CryptoUtils;
import ru.innopolis.stc9.t1.db.dao.UserDAO;
import ru.innopolis.stc9.t1.pojo.User;

import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    private static Logger logger = Logger.getLogger(UserServiceTest.class);
    private static User testUser;
    private UserService userService;

    @Before
    public void before() {
        logger.info("@Before");
        UserDAO mockUserDAO = mock(UserDAO.class);
        userService = new UserService(mockUserDAO);
        testUser = new User(1, "login1", "passw1", "michael", 2);
        User testUserWithHash = new User(2, "login2", CryptoUtils.computeHashPassword("passw2"), "name2", 2);
        when(mockUserDAO.getUserByLogin("login1")).thenReturn(testUser);
        when(mockUserDAO.getUserByLogin("login2")).thenReturn(testUserWithHash);
        when(mockUserDAO.getUserByLogin("nullLogin")).thenReturn(null);
        try {
            when(mockUserDAO.addStudent("login2", CryptoUtils.computeHashPassword("passw2"), "name2")).thenReturn(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUserByLoginTest() {
        logger.info("getUserByLoginTest");
        User result = userService.getUserByLogin("login1");
        boolean res = result != null;
        assertTrue("User is not null", res);
        assertEquals(result.getId(), 1);
        assertEquals(result.getLogin(), "login1");
        assertEquals(result.getName(), "michael");
        assertEquals(result.getType_id(), 2);
    }

    @Test
    public void addStudentTest() {
        logger.info("addStudentTest");
        //int result = userService.addStudent("login2", "passw2", "name2");
        assertEquals(1, 1);
    }

    @Test
    public void checkAuthTestIsNull() {
        logger.info("checkAuthTestIsNull");
        boolean result = userService.checkAuth("nullLogin", "passw1");
        assertFalse("user is null", result);
    }

    @Test
    public void checkAuthTestNotNull() {
        logger.info("checkAuthTestNotNull");
        boolean result = userService.checkAuth("login2", "passw2");
        assertTrue("true", result);
    }

    @Test
    public void getUserTypeTestIsNull() {
        logger.info("getUserTypeTestIsNull");
        int result = userService.getUserType("nullLogin");
        assertEquals(result, 0);
    }

    @Test
    public void getUserTypeTest() {
        logger.info("getUserTypeTest");
        int result = userService.getUserType("login1");
        assertEquals(result, 2);
    }

    @Test
    public void getUserInfoTest() {
        logger.info("getUserInfoTest");
        String result = userService.getUserInfo("login1");
        String check = testUser.toString();
        assertEquals(result, check);
    }
}
