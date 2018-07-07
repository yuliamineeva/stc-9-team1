package ru.innopolis.stc9.t1.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles_h")
public class RoleH {
    private int id;
    private int role_int;
    private String role;
    private String role_description;
    private Set<UserH> users = new HashSet<>();

    public RoleH() {
    }

    public RoleH(int role_int, String role, String role_description) {
        this.role_int = role_int;
        this.role = role;
        this.role_description = role_description;
    }

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRole_int() {
        return role_int;
    }

    public void setRole_int(int role_int) {
        this.role_int = role_int;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole_description() {
        return role_description;
    }

    public void setRole_description(String role_description) {
        this.role_description = role_description;
    }

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<UserH> getUsers() {
        return users;
    }

    public void setUsers(Set<UserH> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "RoleH{" +
                "role_id=" + id +
                ", role_int=" + role_int +
                ", role='" + role + '\'' +
                ", role_description='" + role_description + '\'' +
                '}';
    }
}
