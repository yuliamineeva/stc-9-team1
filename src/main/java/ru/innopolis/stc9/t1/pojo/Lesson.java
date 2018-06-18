package ru.innopolis.stc9.t1.pojo;

import java.util.Date;

public class Lesson {
    private int lsn_id;
    private int tutor_id;
    private int group_id;
    private String topic;
    private Date date;
    private String tutor_name;
    private String group_name;

    public Lesson() {
    }

    public Lesson(int tutor_id, int group_id, String topic, Date date) {
        this.tutor_id = tutor_id;
        this.group_id = group_id;
        this.topic = topic;
        this.date = date;
    }

    public Lesson(int lsn_id, int tutor_id, int group_id, String topic, Date date) {
        this(tutor_id, group_id, topic, date);
        this.lsn_id = lsn_id;
    }

    public Lesson(int lsn_id, int tutor_id, int group_id, String topic, Date date, String tutor_name, String group_name) {
        this(lsn_id, tutor_id, group_id, topic, date);
        this.tutor_name = tutor_name;
        this.group_name = group_name;
    }

    public int getLsn_id() {
        return lsn_id;
    }

    public void setLsn_id(int lsn_id) {
        this.lsn_id = lsn_id;
    }

    public int getTutor_id() {
        return tutor_id;
    }

    public void setTutor_id(int tutor_id) {
        this.tutor_id = tutor_id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTutor_name() {
        return tutor_name;
    }

    public void setTutor_name(String tutor_name) {
        this.tutor_name = tutor_name;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "lsn_id=" + lsn_id +
                ", tutor_id=" + tutor_id +
                ", group_id=" + group_id +
                ", topic='" + topic + '\'' +
                ", date=" + date +
                ", tutor_name='" + tutor_name + '\'' +
                ", group_name='" + group_name + '\'' +
                '}';
    }

}
