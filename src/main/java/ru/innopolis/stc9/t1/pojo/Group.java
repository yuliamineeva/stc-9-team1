package ru.innopolis.stc9.t1.pojo;

import ru.innopolis.stc9.t1.entities.UserH;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "groups_h")
public class Group {

    private int group_id;
    private String name;
    private Set<UserH> users = new HashSet<UserH>();
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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "groups_users",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    public Set<UserH> getUsers() {
        return users;
    }

    public void setUsers(Set<UserH> users) {
        this.users = users;
    }

    public void addUser(UserH user) {
        users.add(user);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group that = (Group) o;
        return group_id == that.group_id &&
                Objects.equals(group_id, that.group_id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(users, that.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(group_id, name, users);
    }
}
