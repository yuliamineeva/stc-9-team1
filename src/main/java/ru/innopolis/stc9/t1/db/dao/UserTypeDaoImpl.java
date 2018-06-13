package ru.innopolis.stc9.t1.db.dao;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.t1.db.connection.ConnectionManager;
import ru.innopolis.stc9.t1.db.connection.ConnectionManagerJDBCImpl;
import ru.innopolis.stc9.t1.pojo.UserType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserTypeDaoImpl implements UserTypeDAO {
    private final static Logger logger = Logger.getLogger(UserTypeDaoImpl.class);
    private static ConnectionManager connectionManager = ConnectionManagerJDBCImpl.getInstance();

    @Override
    public boolean addUserType(UserType userType) {
        Connection connection = connectionManager.getConnection();
        int countRow = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO user_types " +
                    "(type_name) VALUES (?)");
            statement.setString(1, userType.getType_name());
            countRow = statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            logger.error("Error trying to add userType to DB", e);
        }
        return countRow > 0;
    }

    @Override
    public UserType getUserTypeById(int id) {
        Connection connection = connectionManager.getConnection();
        UserType userType = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * " +
                    "FROM user_types WHERE type_id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            userType = getUserTypeFromResultset(resultSet);
            connection.close();
        } catch (SQLException e) {
            logger.error("Error trying to get userType from DB", e);
        }
        return userType;
    }

    private UserType getUserTypeFromResultset(ResultSet resultSet) throws SQLException {
        UserType userType = null;
        if (resultSet.next()) {
            userType = new UserType(
                    resultSet.getInt("type_id"),
                    resultSet.getString("type_name"),
                    resultSet.getString("role"));
        }
        return userType;
    }

    @Override
    public List<UserType> getAllUserTypes() {
        Connection connection = connectionManager.getConnection();
        List<UserType> userTypes = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * " +
                    "FROM user_types");
            userTypes = getUserTypesListFromResultset(resultSet);
            connection.close();
        } catch (SQLException e) {
            logger.error("Error trying to get userTypes from DB", e);
        }
        return userTypes;
    }

    private List<UserType> getUserTypesListFromResultset(ResultSet resultSet) throws SQLException {
        List<UserType> userTypes = new ArrayList<>();
        UserType userType;
        while (resultSet.next()) {
            userType = new UserType(
                    resultSet.getInt("type_id"),
                    resultSet.getString("type_name"),
                    resultSet.getString("role"));
            userTypes.add(userType);
        }
        return userTypes;
    }

    @Override
    public boolean updateUserType(UserType userType) {
        Connection connection = connectionManager.getConnection();
        int countRow = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE user_types " +
                    "SET type_name = ? WHERE type_id = ?");
            statement.setString(1, userType.getType_name());
            statement.setInt(2, userType.getId());
            countRow = statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            logger.error("Error trying to update userType", e);
        }
        return countRow > 0;
    }

    @Override
    public boolean deleteUserTypeById(int id) {
        Connection connection = connectionManager.getConnection();
        int countRow = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM user_types " +
                    "WHERE type_id = ?");
            statement.setInt(1, id);
            countRow = statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            logger.error("Error trying to delete userType", e);
        }
        return countRow > 0;
    }
}
