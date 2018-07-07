package ru.innopolis.stc9.t1.pojo;

import ru.innopolis.stc9.t1.entities.UserH;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lessons_h")
public class Lesson {
    private int lsn_id;
    private String topic;
    private Date date;
    private Date time;
    private Group group;
    private UserH tutor;

    public Lesson() {
    }

    public Lesson(String topic, Date date, Date time, Group group, UserH tutor) {
        this.topic = topic;
        this.date = date;
        this.time = time;
        this.group = group;
        this.tutor = tutor;
    }

    public Lesson(int lsn_id, String topic, Date date, Date time, Group group, UserH tutor) {
        this(topic, date, time, group, tutor);
        this.lsn_id = lsn_id;
    }

    @Id
    @Column(name = "lsn_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getLsn_id() {
        return lsn_id;
    }

    public void setLsn_id(int lsn_id) {
        this.lsn_id = lsn_id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Temporal(TemporalType.TIME)
    @Column(name = "time")
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    public UserH getTutor() {
        return tutor;
    }

    public void setTutor(UserH tutor) {
        this.tutor = tutor;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "lsn_id=" + lsn_id +
                ", topic='" + topic + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", group=" + group +
                ", tutor=" + tutor +
                '}';
    }
}
