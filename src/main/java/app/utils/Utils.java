package app.utils;

import app.dao.TaskDao;
import app.dao.TaskDaoMongo;
import app.dao.TaskDaoRDBMS;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.net.UnknownHostException;

/**
 * Created by andre on 02.04.2017.
 */
public class Utils {
    public static DataSource getDataSource() throws NamingException {
        InitialContext initContext= new InitialContext();
        return (DataSource) initContext.lookup("java:comp/env/jdbc/postgres");
    }

    public static TaskDao getTaskDao() throws NamingException, UnknownHostException {
        return new TaskDaoRDBMS(getDataSource());
//        return new TaskDaoMongo();
    }
}
