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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    private List<Group> allGroups;
    private List<UserH> allTutors;
    private Set<Date> dates;
    private Lesson testLesson;
    private Lesson lesson;

    @Before
    public void before() throws ParseException {
        logger.info("@Before");
        mockMvc = MockMvcBuilders.standaloneSetup(new LessonController(lessonServiceMock, groupServiceMock, userServiceMock)).build();
        Group group1 = new Group(1, "group1");
        Group group2 = new Group(2, "group2");
        allGroups = new ArrayList<>();
        allGroups.add(group1);
        allGroups.add(group2);
        UserH tutor1 = new UserH(1, "tutor1");
        UserH tutor2 = new UserH(2, "tutor2");
        allTutors = new ArrayList<>();
        allTutors.add(tutor1);
        allTutors.add(tutor2);
        String stringDate = "2018-07-10";
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2018-07-10");
        Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse("2018-07-01");
        dates = new HashSet<>();
        dates.add(date);
        dates.add(date2);
        Date time = new SimpleDateFormat("HH:mm:ss").parse("10:00:00");
        Date time2 = new SimpleDateFormat("HH:mm:ss").parse("12:00:00");

        lesson = new Lesson(1, "topic1", date, time, group1, tutor1);
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
        testLesson = new Lesson("topic4", date, time2, group2, tutor2);

        when(lessonServiceMock.getAllLessons()).thenReturn(lessons);
        when(groupServiceMock.getAllGroups()).thenReturn((ArrayList<Group>) allGroups);
        when(userServiceMock.getAllUsersByType(1)).thenReturn(allTutors);
        when(lessonServiceMock.getAllDatesFromLessons()).thenReturn(dates);
        when(lessonServiceMock.getLessonsByGroup(1)).thenReturn(lessonsByGroup);
        when(lessonServiceMock.getLessonsByTutor(2)).thenReturn(lessonsByTutor);
        when(lessonServiceMock.getLessonsByDate(stringDate)).thenReturn(lessons);
        when(groupServiceMock.getGroupById(2)).thenReturn(group2);
        when(userServiceMock.getUser(2)).thenReturn(tutor2);
        when(lessonServiceMock.parseStringDate(stringDate)).thenReturn(date);
        when(lessonServiceMock.addLesson(testLesson)).thenReturn(true);
        when(lessonServiceMock.getLessonById(1)).thenReturn(lesson);
        when(lessonServiceMock.updateLesson(lesson)).thenReturn(true);
        when(lessonServiceMock.deleteLessonById(1)).thenReturn(true);
        when(lessonServiceMock.deleteLessonById(4)).thenReturn(false);
    }

    @Test
    public void getLessonsTest() throws Exception {
        logger.info("getLessonsTest");
        mockMvc.perform(get("/lessons"))
                .andExpect(status().isOk())
                .andExpect(view().name("lessons/lessons"))
                .andExpect(model().attribute("allGroups", hasSize(2)))
                .andExpect(model().attribute("dates", hasSize(2)))
                .andExpect(model().attribute("allTutors", hasSize(2)))
                .andExpect(model().attribute("lessons", hasSize(3)));
    }

    @Test
    public void getLessonsTestByGroup() throws Exception {
        logger.info("getLessonsTestByGroup");
        mockMvc.perform(get("/lessons?lessonsByGroup=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("lessons/lessons"))
                .andExpect(model().attribute("lessons", hasSize(2)));
    }

    @Test
    public void getLessonsTestByDate() throws Exception {
        logger.info("getLessonsTestByDate");
        mockMvc.perform(get("/lessons?lessonsByDate=2018-07-10"))
                .andExpect(status().isOk())
                .andExpect(view().name("lessons/lessons"))
                .andExpect(model().attribute("lessons", hasSize(3)));
    }

    @Test
    public void getLessonsTestByTutor() throws Exception {
        logger.info("getLessonsTestByTutor");
        mockMvc.perform(get("/lessons?lessonsByTutor=2"))
                .andExpect(status().isOk())
                .andExpect(view().name("lessons/lessons"))
                .andExpect(model().attribute("lessons", hasSize(1)));
    }

    @Test
    public void addLessonPageTest() throws Exception {
        logger.info("addLessonPageTest");
        mockMvc.perform(get("/lessons_add"))
                .andExpect(status().isOk())
                .andExpect(view().name("lessons/lessons_add"))
                .andExpect(model().attribute("allGroups", hasSize(2)))
                .andExpect(model().attribute("allTutors", hasSize(2)));
    }

    @Test
    public void addLessonTest() throws Exception {
        logger.info("addLessonTest");
        mockMvc.perform(post("/lessons_add")
                .param("lessonTopic", testLesson.getTopic())
                .param("lessonDate", "2018-07-10")
                .param("lessonTime", "12:00:00")
                .param("lessonGroup", String.valueOf(testLesson.getGroup().getGroup_id()))
                .param("lessonTutor", String.valueOf(testLesson.getTutor().getId())))
                .andExpect(status().isOk())
                .andExpect(view().name("lessons/lessons_add"))
                .andExpect(model().attribute("allGroups", hasSize(2)))
                .andExpect(model().attribute("allTutors", hasSize(2)))
                .andExpect(model().attribute("message", "errAddLesson"));
    }

    @Test
    public void addLessonTestEmpty() throws Exception {
        logger.info("addLessonTestEmpty");
        mockMvc.perform(post("/lessons_add")
                .param("lessonTopic", "")
                .param("lessonDate", "2018-07-10")
                .param("lessonTime", "12:00:00")
                .param("lessonGroup", String.valueOf(testLesson.getGroup().getGroup_id()))
                .param("lessonTutor", String.valueOf(testLesson.getTutor().getId())))
                .andExpect(status().isOk())
                .andExpect(view().name("lessons/lessons_add"))
                .andExpect(model().attribute("message", "EmptyField"));
    }

    @Test
    public void editLessonTestPage() throws Exception {
        logger.info("editLessonTestPage");
        mockMvc.perform(get("/lessons_edit")
                .param("lesson_id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("lessons/lessons_edit"))
                .andExpect(model().attribute("allGroups", hasSize(2)))
                .andExpect(model().attribute("allTutors", hasSize(2)))
                .andExpect(model().attribute("lesson", lesson));
    }

    @Test
    public void editLessonTest() throws Exception {
        logger.info("editLessonTest");
        mockMvc.perform(post("/lessons_edit")
                .param("lessonTopic", lesson.getTopic())
                .param("lessonId", "1")
                .param("lessonDate", "2018-07-10")
                .param("lessonTime", "10:00:00")
                .param("lessonGroup", String.valueOf(lesson.getGroup().getGroup_id()))
                .param("lessonTutor", String.valueOf(lesson.getTutor().getId())))
                .andExpect(status().isOk())
                .andExpect(view().name("lessons/lessons_edit"))
                .andExpect(model().attribute("allGroups", hasSize(2)))
                .andExpect(model().attribute("allTutors", hasSize(2)))
                .andExpect(model().attribute("message", "errEditLesson"));
    }

    @Test
    public void editLessonTestEmpty() throws Exception {
        logger.info("editLessonTestEmpty");
        mockMvc.perform(post("/lessons_edit")
                .param("lessonTopic", "")
                .param("lessonId", "1")
                .param("lessonDate", "2018-07-10")
                .param("lessonTime", "10:00:00")
                .param("lessonGroup", String.valueOf(lesson.getGroup().getGroup_id()))
                .param("lessonTutor", String.valueOf(lesson.getTutor().getId())))
                .andExpect(status().isOk())
                .andExpect(view().name("lessons/lessons_edit"))
                .andExpect(model().attribute("lesson", lesson))
                .andExpect(model().attribute("message", "EmptyField"));
    }

    @Test
    public void getDeleteLessonPageTest() throws Exception {
        logger.info("getDeleteLessonPageTest");
        mockMvc.perform(get("/lessons_delete")
                .param("lesson_id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("lessons/lessons_delete"))
                .andExpect(model().attribute("deleteLesson", lesson));
    }

    @Test
    public void deleteLessonTest() throws Exception {
        logger.info("deleteLessonTest");
        mockMvc.perform(post("/lessons_delete")
                .param("lessonId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("lessons/lessons_delete"))
                .andExpect(model().attribute("lessonId", "1"))
                .andExpect(model().attribute("message", "successfully"))
                .andExpect(model().attribute("lessons", hasSize(3)));
    }

    @Test
    public void deleteLessonTestFalse() throws Exception {
        logger.info("deleteLessonTestFalse");
        mockMvc.perform(post("/lessons_delete")
                .param("lessonId", "4"))
                .andExpect(status().isOk())
                .andExpect(view().name("lessons/lessons_delete"))
                .andExpect(model().attribute("lessonId", "4"))
                .andExpect(model().attribute("message", "errDeleteLesson"))
                .andExpect(model().attribute("lessons", hasSize(3)));
    }
}
