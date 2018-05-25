package ru.innopolis.stc9.t1.pojo;

public class Group {
    private int group_id;
    private String name;

    public Group(int group_id, String name) {
        this.group_id = group_id;
        this.name = name;
    }

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
}
