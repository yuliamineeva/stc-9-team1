package ru.innopolis.stc9.t1.db.dao;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.t1.db.connection.ConnectionManager;
import ru.innopolis.stc9.t1.db.connection.ConnectionManagerJDBCImpl;
import ru.innopolis.stc9.t1.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GroupListDAOImpl implements GroupListDAO {
    private final static Logger logger = Logger.getLogger(GroupListDAOImpl.class);
    private static ConnectionManager connectionManager = ConnectionManagerJDBCImpl.getInstance();
    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public ArrayList<User> getGroupList(int group_id){
        Connection connection = connectionManager.getConnection();
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT u.* FROM users u INNER JOIN group_list gl ON u.user_id = gl.user_id " +
                            "WHERE gl.group_id = ?");
            statement.setInt(1, group_id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                User user = userDAO.getUserById(resultSet.getInt("user_id"));
                if(user != null){
                    users.add(user);
                }
            }
            connection.close();
        } catch (SQLException e) {
            logger.error("Error trying to get All Users By Type from DB", e);
        }
        return users;
    }

}
