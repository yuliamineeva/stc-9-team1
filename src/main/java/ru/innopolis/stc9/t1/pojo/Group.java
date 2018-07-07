package ru.innopolis.stc9.t1.pojo;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "groups_h")
public class Group {

    private int group_id;
    private String name;
    private Set<Lesson> lessons;

    public Group() {
    }

    public Group(int group_id, String name) {
        this.group_id = group_id;
        this.name = name;
    }

    @Id
    @Column(name = "group_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        return name;
    }
}
