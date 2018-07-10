package ru.innopolis.stc9.t1.controller;

import org.apache.log4j.Logger;
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
import ru.innopolis.stc9.t1.pojo.Lesson;
import ru.innopolis.stc9.t1.service.GroupService;
import ru.innopolis.stc9.t1.service.LessonService;
import ru.innopolis.stc9.t1.service.UserServiceH;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class})
@WebAppConfiguration
public class LessonControllerTest {
    private static Logger logger = Logger.getLogger(LessonControllerTest.class);

    MockMvc mockMvc;
    @Autowired
    LessonService lessonServiceMock;
    @Autowired
    GroupService groupServiceMock;
    @Autowired
    UserServiceH userServiceMock;
    private List<Lesson> lessons;
    private List<Group> allgroups;
    private List<UserH> allTutors;
    private Set<Date> dates;

    @Before
    public void before() throws ParseException {
        logger.info("@Before");
        mockMvc = MockMvcBuilders.standaloneSetup(new LessonController(lessonServiceMock, groupServiceMock, userServiceMock)).build();
        Group group1 = new Group(1, "group1");
        Group group2 = new Group(2, "group2");
        allgroups = new ArrayList<>();
        allgroups.add(group1);
        allgroups.add(group2);
        UserH tutor1 = new UserH(1, "tutor1");
        UserH tutor2 = new UserH(2, "tutor2");
        allTutors = new ArrayList<>();
        allTutors.add(tutor1);
        allTutors.add(tutor2);
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2018-07-10");
        Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse("2018-07-01");
        dates = new HashSet<>();
        dates.add(date);
        dates.add(date2);
        Date time = new SimpleDateFormat("HH:mm:ss").parse("10:00:00");
        Date time2 = new SimpleDateFormat("HH:mm:ss").parse("12:00:00");

        Lesson lesson = new Lesson(1, "topic1", date, time, group1, tutor1);
        Lesson lesson2 = new Lesson(2, "topic2", date, time2, group1, tutor1);
        Lesson lesson3 = new Lesson(3, "topic3", date, time, group2, tutor2);
        lessons = new ArrayList<>();
        lessons.add(lesson);
        lessons.add(lesson2);
        lessons.add(lesson3);
        List<Lesson> lessonsByGroup = new ArrayList<>();
        lessonsByGroup.add(lesson);
        lessonsByGroup.add(lesson2);
        List<Lesson> lessonsByTutor = new ArrayList<>();
        lessonsByTutor.add(lesson3);

        when(lessonServiceMock.getAllLessons()).thenReturn(lessons);
        when(groupServiceMock.getAllGroups()).thenReturn((ArrayList<Group>) allgroups);
        when(userServiceMock.getAllUsersByType(1)).thenReturn(allTutors);
        when(lessonServiceMock.getAllDatesFromLessons()).thenReturn(dates);
    }

    @Test
    public void getLessonsTest() throws Exception {
        logger.info("getLessonsTest");
        mockMvc.perform(get("/lessons"))
                .andExpect(status().isOk())
                .andExpect(view().name("lessons/lessons"))
                .andExpect(model().attribute("allGroups", hasSize(2)))
                .andExpect(model().attribute("dates", hasSize(2)))
                .andExpect(model().attribute("allTutors", hasSize(2)));

    }
}
