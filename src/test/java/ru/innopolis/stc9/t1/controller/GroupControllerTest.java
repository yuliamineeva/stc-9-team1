package ru.innopolis.stc9.t1.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.innopolis.stc9.t1.TestContext;
import ru.innopolis.stc9.t1.entities.UserH;
import ru.innopolis.stc9.t1.pojo.Group;
import ru.innopolis.stc9.t1.service.GroupService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class})
@WebAppConfiguration
public class GroupControllerTest {
    MockMvc mockMvc;
    private ArrayList<Group> groupList;

    @Autowired
    GroupService groupService;

    @Before
    public void initTest() {
        groupList = new ArrayList<>();
        mockMvc = MockMvcBuilders.standaloneSetup(new GroupController(groupService)).build();
        Group g1 = new Group(1, "test1");
        Group g2 = new Group(2, "test2");
        groupList.add(g1);
        groupList.add(g2);
        Set<UserH> listUsers = new HashSet<UserH>();
        UserH user1 = new UserH();
        UserH user2 = new UserH();
        listUsers.add(user1);
        listUsers.add(user2);
        Group mockGroup = mock(Group.class);
        when(mockGroup.getName()).thenReturn("test1");
        when(mockGroup.getUsers()).thenReturn(listUsers);
        when(groupService.getAllGroups()).thenReturn(groupList);
        when(groupService.addGroup(new Group(3,"test3"))).thenReturn(true);
        when(groupService.getGroupById(1)).thenReturn(mockGroup);
    }

    @Test
    public void getAllGroupsTest() throws Exception {
        mockMvc.perform(post("/groups"))
                .andExpect(status().isOk())
                .andExpect(view().name("groups/groups"))
                .andExpect(model().attribute("arrayGroup", hasSize(2)));
    }
}
