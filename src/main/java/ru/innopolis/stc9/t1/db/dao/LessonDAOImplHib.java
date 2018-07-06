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
import ru.innopolis.stc9.t1.pojo.Group;
import ru.innopolis.stc9.t1.pojo.Lesson;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
@Primary
public class LessonDAOImplHib implements LessonDAO {
    private final static Logger logger = Logger.getLogger(LessonDAOImplHib.class);

    @Autowired
    private SessionFactory sessionFactory;

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
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Lesson where group.group_id = ?");
        query.setParameter(0, group_id);
        List<Lesson> lessonsByGroup = query.list();
        session.close();
        return lessonsByGroup.size() == 0 ? null : lessonsByGroup;
    }

    @Override
    public List<Lesson> getLessonsByTutor(int tutor_id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Lesson where tutor.id = ?");
        query.setParameter(0, tutor_id);
        List<Lesson> lessonsByTutor = query.list();
        session.close();
        return lessonsByTutor.size() == 0 ? null : lessonsByTutor;
    }

    @Override
    public List<Lesson> getLessonsByDate(Date date) {
        Session session = sessionFactory.openSession();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime() + TimeUnit.DAYS.toMillis(1));
        Query query = session.createQuery("from Lesson where date = ?");
        query.setParameter(0, sqlDate);
        List<Lesson> lessonsByDate = query.list();
        session.close();
        return lessonsByDate.size() == 0 ? null : lessonsByDate;
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
