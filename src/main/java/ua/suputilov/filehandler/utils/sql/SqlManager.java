/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.suputilov.filehandler.utils.sql;

import java.sql.*;

import java.util.Properties;
import java.util.logging.Logger;

/**
 * The class SqlManager represents an object that able to connect to MySql DB
 * and execute inser or update sql queries.
 *
 * @author sergey_putilov
 */
public class SqlManager {

    private final Logger LOG = Logger.getLogger(SqlManager.class.getName());

    private Connection dbConnection;
    private Properties dbConnectionProperties;
    private Statement dbStatement;

    public SqlManager(Properties properties) {
        dbConnectionProperties = properties;
        dbConnection = null;
        dbStatement = null;

        while (!MySqlConnect()) {

            try {
                LOG.info("MySql no connect.");
                Thread.currentThread().sleep(1000);
            } catch (Exception ex) {
                LOG.info(ex.toString());
            }
        }
    }

    public int InsertOrUpdate(String query) {
        int result = 0;

        try {

            if (dbConnection == null) {

                return 0;
            }
            result = dbStatement.executeUpdate(query);
        } catch (SQLException ex) {
            LOG.info("MySqlExeption: " + query);
            LOG.info(ex.toString());
        }

        return result;
    }

    public void Close() {

        try {

            if (((java.sql.Connection) dbConnection) == null
                    || ((java.sql.Connection) dbConnection).isClosed()) {

                return;
            }
            ((java.sql.Connection) dbConnection).close();
        } catch (SQLException ex) {
            LOG.info(ex.toString());
        }
    }

    private boolean MySqlConnect() {
        boolean result = false;
        final String url = dbConnectionProperties.getProperty("db.url");
        final String userName = dbConnectionProperties.getProperty("user");
        final String password = dbConnectionProperties.getProperty("password");

        try {
            dbConnection = DriverManager.getConnection(url, userName, password);
            dbStatement = ((Connection) dbConnection).createStatement();

            result = true;
        } catch (SQLException ex) {
            LOG.info(ex.toString());
        } catch (Exception ex) {
            LOG.info(ex.toString());
        }

        return result;
    }
}
