package ru.innopolis.stc9.t1.pojo;

public class User {
    private int id;
    private String login;
    private String password;
    private String name;
    private int type_id;
    private UserType userType;

    public User(String login, String password, String name, int type_id) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.type_id = type_id;
        this.userType = new UserType(type_id);
    }

    public User(int id, String login, String password, String name, int type_id) {
        this(login, password, name, type_id);
        this.id = id;
    }

    public User(int id, String login, String password, String name, int type_id, UserType userType) {
        this(id, login, password, name, type_id);
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }


    @Override
    public String toString() {
        if (userType != null) {
            return "User: " +
                    "id=" + id +
                    ", login= " + login +
                    ", name= " + name +
                    ", type_id= " + type_id +
                    ", userType= " + userType.getType_name();
        } else {
            return "User: " +
                    "id= " + id +
                    ", login= " + login +
                    ", name= " + name +
                    ", type_id= " + type_id;
        }
    }
}
