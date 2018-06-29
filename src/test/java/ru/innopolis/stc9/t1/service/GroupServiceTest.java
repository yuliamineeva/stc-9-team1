package ru.innopolis.stc9.t1.service;

import org.junit.Before;
import org.junit.Test;
import ru.innopolis.stc9.t1.db.dao.GroupDAO;
import ru.innopolis.stc9.t1.pojo.Group;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GroupServiceTest {
    private GroupService groupService;
    ArrayList<Group> storeGrups = new ArrayList<>();

    @Before
    public void before(){
        storeGrups.clear();
        GroupDAO mockGroupDAO = mock(GroupDAO.class);
        when(mockGroupDAO.getGroupById(1)).thenReturn(new Group(1,"testGroup"));
        when(mockGroupDAO.getGroupByName("testGroup")).thenReturn(new Group(1,"testGroup"));
        ArrayList<Group> groupList = new ArrayList<Group>();
        groupList.add(new Group(1, "testGroup"));
        groupList.add(new Group(2, "testGroup2"));
        when(mockGroupDAO.getAllGroups()).thenReturn(groupList);
        Group mockGroup = mock(Group.class);
        when(mockGroupDAO.addGroup(mockGroup)).thenReturn(storeGrups.add(new Group(11,"test")));
        groupService = new GroupService();
    }

    @Test
    public void getGrouptByIdTest(){
        Group group = groupService.getGroupById(1);
        assertEquals(group.getName(), "testGroup");
        group = groupService.getGroupById(2);
        assertEquals(group, null);
    }

    @Test
    public void getGroupByNameTest(){
        Group group = groupService.getGroupByName("testGroup");
        assertEquals(group.getName(), "testGroup");
        assertEquals(group.getGroup_id(), 1);
        group = groupService.getGroupByName("testGroup2");
        assertEquals(group, null);
    }

    @Test
    public void getAllGroupsTest(){
        ArrayList<Group> arrayG = groupService.getAllGroups();
        Group group = arrayG.get(0);
        assertNotEquals(group, null);
        assertEquals(group.getGroup_id(), 1);
        assertEquals(group.getName(), "testGroup");
        group = arrayG.get(1);
        assertNotEquals(group, null);
        assertEquals(group.getGroup_id(), 2);
        assertEquals(group.getName(), "testGroup2");
    }

    @Test
    public void addGroupTest(){
        Group group = new Group(11,"test");
        groupService.addGroup(group);
        Group groupNew = storeGrups.get(0);
        assertTrue(group.getGroup_id() == groupNew.getGroup_id());
        assertTrue(group.getName().equals(groupNew.getName()));
    }
}
