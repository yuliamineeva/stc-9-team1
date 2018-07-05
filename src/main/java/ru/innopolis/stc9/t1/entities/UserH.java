package ru.innopolis.stc9.t1.entities;

import ru.innopolis.stc9.t1.pojo.Lesson;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users_h")
public class UserH {
    private int id;
    private String login;
    private String password;
    private String name;
    private RoleH role;
    private Set<Lesson> lessons;

    public UserH() {
    }

    public UserH(String login, String password, String name) {
        this.login = login;
        this.password = password;
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

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public RoleH getRole() {
        return role;
    }

    public void setRole(RoleH role) {
        this.role = role;
    }

    @OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY)
    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
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
