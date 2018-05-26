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
    public ArrayList<Group> getAllGroup() {
        return null;
    }


}
