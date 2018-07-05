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

    // insert -------
    @Override
    public int addStudent(String login, String hashPass, String name) throws SQLException {
        int result;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO users (login, passw, fio) VALUES (?, ?, ?)")) {
            statement.setString(1, login);
            statement.setString(2, hashPass);
            statement.setString(3, name);
            result = statement.executeUpdate();
        }
        return result;
    }

    private void setStatementForAdd(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getName());
        statement.setInt(4, user.getType_id());
    }

    // select --------
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
                            resultSet.getString("type_name"),
                            resultSet.getString("role")));
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
    public List<User> getAllUsers() throws SQLException {
        List<User> users;
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users");) {
            users = getUserlistFromResultset(resultSet);
        }
        return users;
    }

    public List<User> getAllUsersNotInGroup(int group_id){
        Connection connection = connectionManager.getConnection();
        List<User> users = null;
        String sqlRequest = "SELECT * FROM users u " +
                            "WHERE NOT EXISTS(select g.list_id from group_list g where g.user_id = u.user_id and g.group_id = ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setInt(1, group_id);
            ResultSet resultSet = statement.executeQuery();
            users = getUserlistFromResultset(resultSet);
            connection.close();
        } catch (SQLException e) {
            logger.error("Error trying to get All Users not in group from DB", e);
        }
        return users;
    }

    // delete -------
    @Override
    public int deleteUserById(int id) throws SQLException {
        int result;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM users WHERE user_id = ?");) {
            statement.setInt(1, id);
            result = statement.executeUpdate();
        }
        return result;
    }

    // update -------
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
    public int updateUserRole(int id, int roleId) throws SQLException {
        int result;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE users SET type_id =? WHERE user_id =?")) {
            statement.setInt(1, roleId);
            statement.setInt(2, id);
            result = statement.executeUpdate();
        }
        return result;
    }

    @Override
    public int updateUserNameByLogin(String login, String name) throws SQLException {
        int result;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE users SET fio =? WHERE login =?")) {
            statement.setString(1, name);
            statement.setString(2, login);
            result = statement.executeUpdate();
        }
        return result;
    }

    @Override
    public int updateUserPasswordByLogin(String login, String hashPassword) throws SQLException {
        int result;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE users SET passw =? WHERE login =?")) {
            statement.setString(1, hashPassword);
            statement.setString(2, login);
            result = statement.executeUpdate();
        }
        return result;
    }

}
