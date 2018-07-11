package ru.innopolis.stc9.t1.entities;

import ru.innopolis.stc9.t1.pojo.Group;
import ru.innopolis.stc9.t1.pojo.Lesson;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users_h")
public class UserH {
    private int id;
    private String login;
    private String password;
    private String name;
    private RoleH role;
    private Set<Group> groups = new HashSet<>();
    private Set<Lesson> lessons;

    public UserH() {
    }

    public UserH(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
    }

    public UserH(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(unique = true)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    public RoleH getRole() {
        return role;
    }

    public void setRole(RoleH role) {
        this.role = role;
    }
    
    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "groups_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "UserH{" +
                "user_id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                '}';
    }
}
