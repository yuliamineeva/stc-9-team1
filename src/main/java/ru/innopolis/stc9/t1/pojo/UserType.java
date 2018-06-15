package ru.innopolis.stc9.t1.pojo;

public class UserType {
    private int id;
    private String type_name;
    private String role;

    public UserType(int id) {
        this.id = id;
        switch (id) {
            case 1:
                type_name = "преподаватель";
                role = "ROLE_TEACHER";
                break;
            case 2:
                type_name = "студент";
                role = "ROLE_USER";
                break;
            case 3:
                type_name = "админ";
                role = "ROLE_ADMIN";
                break;
        }
    }

    public UserType(int id, String type_name, String role) {
        this.id = id;
        this.type_name = type_name;
        this.role = role;
    }

    public UserType(String type_name) {
        this.type_name = type_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserType{" +
                "id=" + id +
                ", type_name='" + type_name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
