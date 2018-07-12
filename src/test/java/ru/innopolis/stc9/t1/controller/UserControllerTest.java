package ru.innopolis.stc9.t1.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.innopolis.stc9.t1.ErrorMsgHandler;
import ru.innopolis.stc9.t1.TestContext;
import ru.innopolis.stc9.t1.entities.UserH;
import ru.innopolis.stc9.t1.service.UserServiceH;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class})
@WebAppConfiguration
public class UserControllerTest {
    //private static Logger logger = Logger.getLogger(LessonControllerTest.class);

    MockMvc mockMvc;
    @Autowired
    UserServiceH userServiceMock;
    @Autowired
    ErrorMsgHandler errorMsgHandler;
    private UserH testUser;

    @Before
    public void before() {
        testUser = new UserH("login", "password", "name");
        when(userServiceMock.getUserByLogin("login")).thenReturn(testUser);
    }

    @Test
    public void loginTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login")).
                andExpect(view().name("login")).
                andExpect(status().isOk());
    }

    @Test
    public void registrationEmptyInputTest() throws Exception {
        mockMvc.perform(post("/registration")
                .param("userLogin", "login")
                .param("userName", "name")
                .param("userPassword", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"))
                .andExpect(model().attribute("reply", "заполните все поля"));
    }

    @Test
    public void registrationTest() throws Exception {
        mockMvc.perform(post("/registration")
                .param("userLogin", "login")
                .param("userName", "name")
                .param("userPassword", "password"))
                .andExpect(view().name("registration"))
                .andExpect(model().attribute("reply", "user login has been registered, you can login"));
    }

    @Test
    public void getProfileTest() throws Exception {
        UserH currentUser = userServiceMock.getUserByLogin("login");
        mockMvc.perform(post("/profile"))
                .andExpect(status().isOk())
                .andExpect(view().name("users/profile"))
                .andExpect(model().attribute("user", currentUser));
    }

    @Test
    public void setProfileChangeNameTest() throws Exception {
        mockMvc.perform(post("/profileChangeName"))
                .andExpect(status().isOk())
                .andExpect(view().name("users/profile"))
                .andExpect(model().attribute("nameChanged", true));
    }

    @Test
    public void setProfileChangePassTest() throws Exception {
        mockMvc.perform(post("/profileChangePass"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attribute("passChanged", true));
    }
}
