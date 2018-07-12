package ru.innopolis.stc9.t1.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.innopolis.stc9.t1.db.dao.GroupDAO;
import ru.innopolis.stc9.t1.db.dao.GroupDAOImplH;
import ru.innopolis.stc9.t1.pojo.Group;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(PowerMockRunner.class)
@PrepareForTest({GroupService.class})
public class GroupServiceTest {
    private GroupService groupService;
    private Group group;
    private GroupDAO groupDao;
    private List<Group> listGroups;

    @Before
    public void initTest() throws Exception {
        group = new Group(1, "test1");
        groupService = new GroupService(groupDao);
        groupDao = PowerMockito.mock(GroupDAOImplH.class);
        Field field = PowerMockito.field(GroupService.class, "groupDAO");
        field.set(groupService, groupDao);
        listGroups = new ArrayList<>();
    }

    @Test
    public void addGroupTest() throws Exception {
        PowerMockito.when(groupDao.addGroup(group)).thenReturn(true);
        assertTrue(groupService.addGroup(group));
    }

    @Test
    public void getGroupByIdTest() throws Exception {
        PowerMockito.when(groupDao.getGroupById(1)).thenReturn(group);
        assertEquals(groupService.getGroupById(1), group);
    }

    @Test
    public void getAllGroupsTest() throws Exception {
        PowerMockito.when(groupDao.getAllGroups()).thenReturn(listGroups);
        assertEquals(groupService.getAllGroups(), listGroups);
    }

    @Test
    public void getGroupByNameTest(){
        PowerMockito.when(groupDao.getGroupByName("test1")).thenReturn(group);
        Group groupTest = groupService.getGroupByName("test1");
        assertEquals(groupTest.getName(), "test1");
        assertEquals(groupTest.getGroup_id(), 1);
    }

    @Test
    public void updateGroupTest() throws Exception {
        PowerMockito.when(groupDao.updateGroup(group)).thenReturn(true);
        assertTrue(groupService.updateGroup(group));
    }

    @Test
    public void deleteGroup() throws Exception {
        PowerMockito.when(groupDao.deleteGroup(1)).thenReturn(true);
        assertTrue(groupService.deleteGroup(1));
    }

}
