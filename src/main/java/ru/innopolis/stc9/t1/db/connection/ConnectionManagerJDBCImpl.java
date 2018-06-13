package ru.innopolis.stc9.t1.db.connection;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagerJDBCImpl implements ConnectionManager {
    private static ConnectionManager connectionManager;
    private final static Logger logger = Logger.getLogger(ConnectionManagerJDBCImpl.class);

    private String db_driverClassName = null;
    private String db_url = null;
    private String db_user = null;
    private String db_pass = null;

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
        readXmlDbOptions();
        try{
            Class.forName(db_driverClassName);
        }catch(ClassNotFoundException ex){
            logger.fatal("error register DB driver class", ex);
        }
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(db_url,db_user,db_pass);
        } catch (ClassNotFoundException e) {
            logger.error("error to get connection",e);
        } catch (SQLException e) {
            logger.error("error to get connection",e);
        }
        return connection;
    }

    /** Функция чтения настроек БД из файла */
    private void readXmlDbOptions(){
        try{
            String path = "datasource-config.xml";
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream(path);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(input);
            doc.getDocumentElement().normalize();

            Node nodeRoot = doc.getDocumentElement();
            NodeList rootList = nodeRoot.getChildNodes();
            Node bean = null;
            for(int i=0; i<rootList.getLength();i++){
                if(rootList.item(i).getNodeName().equals("bean")){
                    bean = rootList.item(i);
                    break;
                }
            }
            NodeList properties = bean.getChildNodes();
            for(int i=0; i<properties.getLength();i++){
                if(properties.item(i).getNodeName().equals("property")){
                    Node prop_name = properties.item(i).getAttributes().getNamedItem("name");
                    String propName = prop_name.getTextContent();
                    Node prop_val = properties.item(i).getAttributes().getNamedItem("value");
                    String propValue = prop_val.getTextContent();
                    switch(propName){
                        case "driverClassName": db_driverClassName = propValue;
                            break;
                        case "url": db_url = propValue;
                            break;
                        case "username": db_user = propValue;
                            break;
                        case "password": db_pass = propValue;
                            break;
                    }
                }
            }
        }catch(ParserConfigurationException | SAXException | IOException ex){
            logger.fatal("Unable connect to options_DB.xml", ex);
        }
    }
}
