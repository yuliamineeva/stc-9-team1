package ru.innopolis.stc9.t1.db.dao;

import ru.innopolis.stc9.t1.entities.UserH;
import ru.innopolis.stc9.t1.pojo.Group;
import ru.innopolis.stc9.t1.pojo.Lesson;

import java.util.Date;
import java.util.List;

public interface LessonDAO {

    boolean addLesson(Lesson lesson);

    Lesson getLessonById(int id);

    Group getGroupById(int id);

    UserH getTutorById(int id);

    List<Lesson> getAllLessons();

    List<Lesson> getLessonsByGroup(int group_id);

    List<Lesson> getLessonsByTutor(int tutor_id);

    List<Lesson> getLessonsByDate(Date date);

    boolean updateLesson(Lesson lesson);

    boolean deleteLessonById(int id);
}
