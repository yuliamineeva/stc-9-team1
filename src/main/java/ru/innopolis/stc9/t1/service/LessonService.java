package ru.innopolis.stc9.t1.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.t1.db.dao.LessonDAO;
import ru.innopolis.stc9.t1.db.dao.LessonDAOImpl;
import ru.innopolis.stc9.t1.pojo.Lesson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LessonService {
    private final static Logger logger = Logger.getLogger(LessonService.class);
    private LessonDAO lessonDAO;

    public LessonService() {
        lessonDAO = new LessonDAOImpl();
    }

    public LessonService(LessonDAO lessonDAO) {
        this.lessonDAO = lessonDAO;
    }

    public boolean addLesson(Lesson lesson) {
        return lessonDAO.addLesson(lesson);
    }

    public Lesson getLessonById(int id) {
        return lessonDAO.getLessonById(id);
    }

    public List<Lesson> getAllLessons() {
        return lessonDAO.getAllLessons();
    }

    public List<Lesson> getLessonsByGroup(int group_id) {
        return lessonDAO.getLessonsByGroup(group_id);
    }

    public List<Lesson> getLessonsByTutor(int tutor_id) {
        return lessonDAO.getLessonsByTutor(tutor_id);
    }

    public List<Lesson> getLessonsByDate(String date) {
        List<Lesson> listLessonsByDate = null;
        try {
            Date lessonDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            listLessonsByDate = lessonDAO.getLessonsByDate(lessonDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listLessonsByDate;
    }

    public boolean updateLesson(Lesson lesson) {
        return lessonDAO.updateLesson(lesson);
    }

    public boolean deleteLessonById(int id) {
        return lessonDAO.deleteLessonById(id);
    }

    /**
     * Получить список всех дат, в которые были лекции
     *
     * @return List<Date>
     */
    public Set<Date> getAllDatesFromLessons() {
        Set<Date> dates = new HashSet<>();
        List<Lesson> lessons = lessonDAO.getAllLessons();
        for (Lesson lesson : lessons) {
            Date date = lesson.getDate();
            dates.add(date);
            System.out.println(date);
        }
        return dates;
    }

}
