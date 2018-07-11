package ru.innopolis.stc9.t1.service;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.innopolis.stc9.t1.db.dao.LessonDAO;
import ru.innopolis.stc9.t1.db.dao.LessonDAOImplHib;
import ru.innopolis.stc9.t1.entities.UserH;
import ru.innopolis.stc9.t1.pojo.Group;
import ru.innopolis.stc9.t1.pojo.Lesson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


public class LessonServiceTest {
    private static Logger logger = Logger.getLogger(LessonServiceTest.class);
    private static Lesson lesson;
    private LessonService lessonService;
    private Date date;
    private Date time;
    private Group group1;
    private UserH tutor1;
    private List<Lesson> lessons;

    @Before
    public void before() throws ParseException {
        logger.info("@Before");
        group1 = new Group(1, "group1");
        Group group2 = new Group(2, "group2");
        tutor1 = new UserH(1, "tutor1");
        UserH tutor2 = new UserH(2, "tutor2");
        date = new SimpleDateFormat("yyyy-MM-dd").parse("2018-07-10");
        Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse("2018-07-01");
        time = new SimpleDateFormat("HH:mm:ss").parse("10:00:00");
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

        LessonDAO mockLessonDao = Mockito.mock(LessonDAOImplHib.class);
        lessonService = new LessonService(mockLessonDao);
        when(mockLessonDao.addLesson(lesson)).thenReturn(true);
        when(mockLessonDao.getLessonById(1)).thenReturn(lesson);
        when(mockLessonDao.deleteLessonById(1)).thenReturn(true);
        when(mockLessonDao.deleteLessonById(5)).thenReturn(false);
        when(mockLessonDao.getAllLessons()).thenReturn(lessons);
        when(mockLessonDao.getLessonsByGroup(1)).thenReturn(lessonsByGroup);
        when(mockLessonDao.getLessonsByTutor(2)).thenReturn(lessonsByTutor);
        when(mockLessonDao.getLessonsByDate(date)).thenReturn(lessons);
        when(mockLessonDao.getLessonsByDate(date2)).thenReturn(null);
        when(mockLessonDao.updateLesson(lesson)).thenReturn(true);
    }

    @Test
    public void addLessonTestNull() {
        logger.info("addLessonTestNull");
        boolean result = lessonService.addLesson(null);
        assertFalse(result);
    }

    @Test
    public void addLessonTestNotNull() {
        logger.info("addLessonTestNotNull");
        boolean result = lessonService.addLesson(lesson);
        assertTrue(result);
    }

    @Test
    public void getLessonByIdTest() {
        logger.info("getLessonByIdTest");
        Lesson result = lessonService.getLessonById(1);
        boolean res = result != null;
        assertTrue("User is not null", res);
        assertEquals(result.getLsn_id(), 1);
        assertEquals(result.getTopic(), "topic1");
        assertEquals(result.getDate(), date);
        assertEquals(result.getTime(), time);
        assertEquals(result.getGroup(), group1);
        assertEquals(result.getTutor(), tutor1);
    }

    @Test
    public void getAllLessonsTest() {
        logger.info("getAllLessonsTest");
        List<Lesson> result = lessonService.getAllLessons();
        assertEquals(result.size(), lessons.size());
        assertEquals(result.get(0).getLsn_id(), 1);
        assertEquals(result.get(0).getTopic(), "topic1");
        assertEquals(result.get(0).getDate(), date);
        assertEquals(result.get(0).getTime(), time);
        assertEquals(result.get(0).getGroup(), group1);
        assertEquals(result.get(0).getTutor(), tutor1);
    }

    @Test
    public void getLessonsByGroupTest() {
        logger.info("getLessonsByGroupTest");
        List<Lesson> result = lessonService.getLessonsByGroup(1);
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getLsn_id(), 1);
        assertEquals(result.get(0).getTopic(), "topic1");
        assertEquals(result.get(0).getDate(), date);
        assertEquals(result.get(0).getTime(), time);
        assertEquals(result.get(0).getGroup(), group1);
        assertEquals(result.get(0).getTutor(), tutor1);
    }

    @Test
    public void getLessonsByTutorTest() {
        logger.info("getLessonsByTutorTest");
        List<Lesson> result = lessonService.getLessonsByTutor(2);
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getLsn_id(), 3);
        assertEquals(result.get(0).getTopic(), "topic3");
        assertEquals(result.get(0).getDate(), date);
        assertEquals(result.get(0).getTime(), time);
        assertEquals(result.get(0).getGroup().getName(), "group2");
        assertEquals(result.get(0).getTutor().getName(), "tutor2");
    }

    @Test
    public void getLessonsByDateTest() {
        logger.info("getLessonsByDateTest");
        List<Lesson> result = lessonService.getLessonsByDate("2018-07-10");
        assertEquals(result.size(), lessons.size());
        assertEquals(result.get(0).getLsn_id(), 1);
        assertEquals(result.get(0).getTopic(), "topic1");
        assertEquals(result.get(0).getDate(), date);
        assertEquals(result.get(0).getTime(), time);
        assertEquals(result.get(0).getGroup(), group1);
        assertEquals(result.get(0).getTutor(), tutor1);
    }

    @Test
    public void getLessonsByDateTestFalse() {
        logger.info("getLessonsByDateTestFalse");
        List<Lesson> result = lessonService.getLessonsByDate("2018-07-01");
        assertNull(result);
    }

    @Test
    public void getLessonsByDateTestExc() {
        logger.info("getLessonsByDateTestExc");
        List<Lesson> result = lessonService.getLessonsByDate("2018/07/10");
        assertNotEquals(result, date);
    }

    @Test
    public void updateLessonTestNull() {
        logger.info("updateLessonTestNull");
        boolean result = lessonService.updateLesson(null);
        assertFalse(result);
    }

    @Test
    public void updateLessonTestNotNull() {
        logger.info("updateLessonTestNotNull");
        boolean result = lessonService.updateLesson(lesson);
        assertTrue(result);
    }

    @Test
    public void deleteLessonByIdTest() {
        logger.info("deleteLessonByIdTest");
        boolean result = lessonService.deleteLessonById(1);
        assertTrue(result);
    }

    @Test
    public void deleteLessonByIdTestNotExist() {
        logger.info("deleteLessonByIdTestNotExist");
        boolean result = lessonService.deleteLessonById(5);
        assertFalse(result);
    }

    @Test
    public void getAllDatesFromLessonsTest() {
        logger.info("getAllDatesFromLessonsTest");
        List<Date> datesList = new ArrayList<>();
        datesList.add(date);
        Set<Date> result = lessonService.getAllDatesFromLessons();
        List<Date> resultList = new ArrayList<>();
        resultList.addAll(result);
        assertEquals(result.size(), datesList.size());
        assertEquals(resultList.get(0), datesList.get(0));
    }

    @Test
    public void parseStringDateTest() {
        logger.info("parseStringDateTest");
        String stringDate = "2018-07-09";
        Date result = lessonService.parseStringDate(stringDate);
        assertEquals(result, date);
    }

    @Test
    public void parseStringDateTestExc() {
        logger.info("parseStringDateTestExc");
        String stringDate = "2018/07/09";
        Date result = lessonService.parseStringDate(stringDate);
        assertNotEquals(result, date);
    }
}
