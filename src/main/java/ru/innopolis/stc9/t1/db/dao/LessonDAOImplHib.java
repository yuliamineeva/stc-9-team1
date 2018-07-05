package ru.innopolis.stc9.t1.db.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.t1.entities.UserH;
import ru.innopolis.stc9.t1.pojo.Group;
import ru.innopolis.stc9.t1.pojo.Lesson;

import java.util.Date;
import java.util.List;

@Repository
@Primary
public class LessonDAOImplHib implements LessonDAO {
    private final static Logger logger = Logger.getLogger(LessonDAOImplHib.class);

    @Autowired
    private SessionFactory sessionFactory;

//    public LessonDAOImplHib() {
//    }

    @Override
    public boolean addLesson(Lesson lesson) {
        if (lesson == null) {
            return false;
        }
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(lesson);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Lesson getLessonById(int id) {
        Session session = sessionFactory.openSession();
        Lesson lesson = (Lesson) session.get(Lesson.class, id);
        session.close();
        return lesson;
    }

    @Override
    public Group getGroupById(int id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Group WHERE group_id = ?");
        query.setParameter(0, id);
        Group group = (Group) query.list().get(0);
        session.close();
        return group;
    }

    @Override
    public UserH getTutorById(int id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM UserH WHERE id = ?");
        query.setParameter(0, id);
        UserH tutor = (UserH) query.list().get(0);
        session.close();
        return tutor;
    }

    @Override
    public List<Lesson> getAllLessons() {
        List<Lesson> lessons = null;
        try {
            Session session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Lesson.class);
            lessons = criteria.list();
            session.close();
        } catch (HibernateException | NullPointerException e) {
            logger.error("error to get all lessons", e);
        }
        return lessons;
    }


    @Override
    public List<Lesson> getLessonsByGroup(int group_id) {
        List<Lesson> lessonsByGroup = null;
        try {
            Session session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Lesson.class);
            criteria.add(Restrictions.eq("group_id", group_id));
            if (criteria.list() == null || criteria.list().size() == 0) return null;
            lessonsByGroup = criteria.list();

//            NativeQuery query = session.createNativeQuery("SELECT l.* FROM public.lessons_h l where l.group_id='" + group_id + "'");
//            query.addEntity(Lesson.class);
//            lessonsByGroup = query.list();
            session.close();
        } catch (HibernateException e) {
            logger.error("error to get lessons by group", e);
        }
        return lessonsByGroup;
    }

    @Override
    public List<Lesson> getLessonsByTutor(int tutor_id) {
        Session session = sessionFactory.openSession();
        NativeQuery query = session.createNativeQuery("SELECT l.* FROM public.lessons_h l where l.tutor_id='" + tutor_id + "'");
        query.addEntity(Lesson.class);
        List<Lesson> lessonsByTutor = query.list();
        session.close();
        return lessonsByTutor;
    }

    @Override
    public List<Lesson> getLessonsByDate(Date date) {
        Session session = sessionFactory.openSession();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        List<Lesson> lessonsByDate = session.createCriteria(Lesson.class)
                .add(Restrictions.sqlRestriction("WHERE DATE(lessons.date) = '" + sqlDate + "'" + "INTERVAL 1 DAY"))
                .list();
        session.close();
        return lessonsByDate;
    }

    @Override
    public boolean updateLesson(Lesson lesson) {
        if (lesson == null) {
            return false;
        }
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(lesson);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean deleteLessonById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Lesson lesson = (Lesson) session.load(Lesson.class, id);
        session.delete(lesson);
        session.getTransaction().commit();
        session.close();
        return true;
    }
}
