package ru.innopolis.stc9.t1.db.connection;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagerJDBCImpl implements ConnectionManager {
    private static ConnectionManager connectionManager;
    private final static Logger logger = Logger.getLogger(ConnectionManagerJDBCImpl.class);

    public static ConnectionManager getInstance(){
        if (connectionManager == null){
            synchronized (ConnectionManagerJDBCImpl.class){
                if(connectionManager == null) {
                    connectionManager = new ConnectionManagerJDBCImpl();
                }
            }
        }
        return connectionManager;
    }

    private ConnectionManagerJDBCImpl(){
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://sql7.freesqldatabase.com:3306/sql7239355",
                    "sql7239355",
                    "IRMAcXvBY6");
        } catch (ClassNotFoundException e) {
            logger.error("error to get connection",e);
        } catch (SQLException e) {
            logger.error("error to get connection",e);
        }
        return connection;
    }
}
