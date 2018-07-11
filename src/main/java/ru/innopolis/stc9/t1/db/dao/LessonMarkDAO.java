package ru.innopolis.stc9.t1.db.dao;

import ru.innopolis.stc9.t1.pojo.LessonMark;
import ru.innopolis.stc9.t1.pojo.User;

import java.util.List;

public interface LessonMarkDAO {

    public LessonMark getLessonMarkByUsers(User user);

    public List<LessonMark> getAllLessonsMark();

    public boolean addLessonMark(LessonMark lessonsMark);

    public boolean updateLessons(LessonMark lessonsMark);
    public boolean deleteLessons(LessonMark lessonsMark);

    public LessonMark getLessonMarkById(int id);


}
