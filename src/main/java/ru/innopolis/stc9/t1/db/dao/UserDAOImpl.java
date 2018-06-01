package ru.innopolis.stc9.t1.db.dao;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.t1.db.connection.ConnectionManager;
import ru.innopolis.stc9.t1.db.connection.ConnectionManagerJDBCImpl;
import ru.innopolis.stc9.t1.pojo.User;
import ru.innopolis.stc9.t1.pojo.UserType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private final static Logger logger = Logger.getLogger(UserDAOImpl.class);
    private static ConnectionManager connectionManager = ConnectionManagerJDBCImpl.getInstance();

    @Override
    public boolean addUser(User user) {
        Connection connection = connectionManager.getConnection();
        int countRow = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users " +
                    "(login, passw, fio, type_id) VALUES (?, ?, ?, ?)");
            setStatementForAdd(user, statement);
            countRow = statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            logger.error("Error trying to add user to DB", e);
        }
        return countRow > 0;
    }

    private void setStatementForAdd(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getName());
        statement.setInt(4, user.getType_id());
    }

    @Override
    public User getUserById(int id) {
        Connection connection = connectionManager.getConnection();
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * " +
                    "FROM users INNER JOIN user_types " +
                    "ON users.type_id = user_types.type_id WHERE users.user_id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            user = getUserFromResultset(resultSet);
            connection.close();
        } catch (SQLException e) {
            logger.error("Error trying to get user by ID from DB", e);
        }
        return user;
    }

    private User getUserFromResultset(ResultSet resultSet) throws SQLException {
        User user = null;
        if (resultSet.next()) {
            user = new User(
                    resultSet.getInt("user_id"),
                    resultSet.getString("login"),
                    resultSet.getString("passw"),
                    resultSet.getString("fio"),
                    resultSet.getInt("type_id"),
                    new UserType(
                            resultSet.getInt("type_id"),
                            resultSet.getString("type_name")));
        }
        return user;
    }


    @Override
    public User getUserByLogin(String login) {
        Connection connection = connectionManager.getConnection();
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * " +
                    "FROM users INNER JOIN user_types " +
                    "ON users.type_id = user_types.type_id  WHERE users.login = ?");
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            user = getUserFromResultset(resultSet);
            connection.close();
        } catch (SQLException e) {
            logger.error("Error trying to get user login ID from DB", e);
        }
        return user;
    }

    @Override
    public List<User> getAllUsersByType(int type) {
        Connection connection = connectionManager.getConnection();
        List<User> users = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * " +
                    "FROM users WHERE users.type_id = ?");
            statement.setInt(1, type);
            ResultSet resultSet = statement.executeQuery();
            users = getUserlistFromResultset(resultSet);
            connection.close();
        } catch (SQLException e) {
            logger.error("Error trying to get All Users By Type from DB", e);
        }
        return users;
    }

    private List<User> getUserlistFromResultset(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        User user;
        while (resultSet.next()) {
            user = new User(
                    resultSet.getInt("user_id"),
                    resultSet.getString("login"),
                    resultSet.getString("passw"),
                    resultSet.getString("fio"),
                    resultSet.getInt("type_id"));
            users.add(user);
        }
        return users;
    }

    @Override
    public List<User> getAllUsers() {
        Connection connection = connectionManager.getConnection();
        List<User> users = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * " +
                    "FROM users");
            users = getUserlistFromResultset(resultSet);
            connection.close();
        } catch (SQLException e) {
            logger.error("Error trying to get All Users from DB", e);
        }
        return users;
    }

    @Override
    public boolean updateUser(User user) {
        Connection connection = connectionManager.getConnection();
        int countRow = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE users " +
                    "SET login = ?, passw = ?, fio = ?, type_id = ? WHERE user_id = ?");
            setStatementForUpdate(user, statement);
            countRow = statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            logger.error("Error trying to update user", e);
        }
        return countRow > 0;
    }

    private void setStatementForUpdate(User user, PreparedStatement statement) throws SQLException {
        setStatementForAdd(user, statement);
        statement.setInt(5, user.getId());
    }

    @Override
    public boolean deleteUserById(int id) {
        Connection connection = connectionManager.getConnection();
        int countRow = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users " +
                    "WHERE user_id = ?");
            statement.setInt(1, id);
            countRow = statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            logger.error("Error trying to delete user", e);
        }
        return countRow > 0;
    }

}
