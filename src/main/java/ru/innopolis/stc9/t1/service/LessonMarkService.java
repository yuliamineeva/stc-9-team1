package ru.innopolis.stc9.t1.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.t1.db.dao.LessonMarkDAO;
import ru.innopolis.stc9.t1.pojo.LessonMark;
import ru.innopolis.stc9.t1.pojo.User;

import java.util.List;

@Service
public class LessonMarkService {
    private final static Logger logger = Logger.getLogger(LessonMarkService.class);

    @Autowired
    private LessonMarkDAO lessonMarkDAO;

    public LessonMarkService() {
    }


    public boolean addLessonMark(LessonMark lessonMark) {
        return lessonMarkDAO.addLessonMark(lessonMark);
    }

    public boolean deleteLessonMark(LessonMark lessonMark) {
        return lessonMarkDAO.deleteLessons(lessonMark);
    }

    public LessonMark getLessonMarkById(int id) {
        return lessonMarkDAO.getLessonMarkById(id);
    }

    public List<LessonMark> getAllLessonMark() {
        List<LessonMark> lessonMarks = lessonMarkDAO.getAllLessonsMark();
        return lessonMarks;
    }

    public boolean updateLessonMark(LessonMark lessonMark) {
        return lessonMarkDAO.updateLessons(lessonMark);
    }

    public LessonMark getLessonMarkByUser(User user){
        return  lessonMarkDAO.getLessonMarkByUsers(user);
    }


}
