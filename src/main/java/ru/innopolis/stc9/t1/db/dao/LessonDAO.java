package ru.innopolis.stc9.t1.db.dao;

import ru.innopolis.stc9.t1.pojo.Lesson;

import java.util.List;

public interface LessonDAO {

    boolean addLesson(Lesson lesson);

    Lesson getLessonById(int id);

    List<Lesson> getAllLessons();

    List<Lesson> getLessonsByGroup(int tutor_id);

    List<Lesson> getLessonsByTutor(int tutor_id);

    boolean updateLesson(Lesson lesson);

    boolean deleteLessonById(int id);
}
