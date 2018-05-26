package ru.innopolis.stc9.t1.db.dao;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.t1.db.connection.ConnectionManager;
import ru.innopolis.stc9.t1.db.connection.ConnectionManagerJDBCImpl;
import ru.innopolis.stc9.t1.pojo.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GroupDAOImpl implements GroupDAO {
    private static ConnectionManager connectionManager = ConnectionManagerJDBCImpl.getInstance();
    private final static Logger logger = Logger.getLogger(GroupDAOImpl.class);

    private Group createGroup(ResultSet resultSet){
        Group group = null;
        try{
            group = new Group(
                    resultSet.getInt("group_id"),
                    resultSet.getString("name"));
        }catch (SQLException ex) {
            logger.error("Error to create new Group",ex);
            return null;
        }
        return group;
    }

    @Override
    public Group getGroupById(int id) {
        Group group = null;
        String sqlRequest = "SELECT * FROM groups WHERE group_id = ?";
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                group = createGroup(resultSet);
            }
            connection.close();
        }catch (SQLException ex) {
            logger.error("Error to get Group by ID",ex);
        }
        return group;
    }

    @Override
    public Group getGroupByName(String name){
        Group group = null;
        String sqlRequest = "SELECT * FROM groups WHERE name = ?";
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                group = createGroup(resultSet);
            }
            connection.close();
        }catch (SQLException ex) {
            logger.error("Error to get Group by Name",ex);
        }
        return group;
    }

    @Override
    public ArrayList<Group> getAllGroups() {
        ArrayList<Group> arrayGroup = new ArrayList<>();
        String sqlRequest = "SELECT * FROM groups";
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                arrayGroup.add(createGroup(resultSet));
            }
            connection.close();
        }catch (SQLException ex) {
            logger.error("Error to get all groups",ex);
        }
        return arrayGroup;
    }

    @Override
    public boolean addGroup(Group group) {
        if(getGroupByName(group.getName()) != null)return false;
        String sqlRequest = "INSERT INTO groups (name) VALUES(?)";
        int result = 0;
        Connection connection = connectionManager.getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setString(1, group.getName());
            result = statement.executeUpdate();
            connection.close();
        }catch (SQLException ex) {
            logger.error("Error adding group to DB",ex);
            return false;
        }
        return (result>0);
    }

    @Override
    public boolean deleteGroup(int id) {
        String sqlRequest = "DELETE FROM groups WHERE group_id = ?";
        int result = 0;
        Connection connection = connectionManager.getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setInt(1, id);
            result = statement.executeUpdate();
            connection.close();
        }catch (SQLException ex) {
            logger.error("Error delete group from DB",ex);
            return false;
        }
        return (result>0);
    }
}
