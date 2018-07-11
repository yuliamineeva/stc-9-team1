package ru.innopolis.stc9.t1.db.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.t1.entities.UserH;
import ru.innopolis.stc9.t1.pojo.Lesson;
import ru.innopolis.stc9.t1.pojo.LessonMark;
import ru.innopolis.stc9.t1.pojo.User;
import ru.innopolis.stc9.t1.service.LessonService;
import ru.innopolis.stc9.t1.service.UserServiceH;

import java.util.ArrayList;
import java.util.List;

@Repository
@Primary
public class LessonMarkDAOImplHib implements LessonMarkDAO {


    private final static Logger logger = Logger.getLogger(LessonMarkDAOImplHib.class);

    @Autowired
    private SessionFactory sessionFactory;


    @Autowired
    private LessonService lessonService;

    @Autowired
    private UserServiceH userServiceH;

    @Override
    public LessonMark getLessonMarkByUsers(User user) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("Select * FROM Group WHERE user_id = ?");
        query.setParameter(0, user.getId());
        LessonMark lessonMark = (LessonMark) query.list().get(0);
        session.close();
        return lessonMark;
    }

    @Override
    public List<LessonMark> getAllLessonsMark() {
        List<LessonMark> lessonMarks =  new ArrayList<>();
        try {
            Session session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(LessonMark.class);
            lessonMarks = criteria.list();
            session.close();
        } catch (HibernateException | NullPointerException e) {
            logger.error("error to get all lessons", e);
        }
        return lessonMarks;
    }

    @Override
    public boolean addLessonMark(LessonMark lessonsMark) {
        if (lessonsMark == null) {
            return false;
        }
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(lessonsMark);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean updateLessons(LessonMark lessonsMark) {
        if (lessonsMark == null) {
            return false;
        }
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(lessonsMark);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean deleteLessons(LessonMark lessonsMark) {
        if (lessonsMark == null) {
            return false;
        }
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(lessonsMark);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public LessonMark getLessonMarkById(int id) {
        Session session = sessionFactory.openSession();
        LessonMark lessonMark = (LessonMark) session.get(LessonMark.class, id);
        session.close();
        return lessonMark;
    }


}