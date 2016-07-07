/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.suputilov.filehandler.utils.sql;

import java.util.List;
import java.util.Properties;
import org.apache.commons.lang3.StringEscapeUtils;
import ua.suputilov.filehandler.entities.Line;
import ua.suputilov.filehandler.entities.TextFile;

/**
 * The class DbWriter represents an object that able to create needed SQL
 * queries to save date to the DB.
 *
 * @author sergey_putilov
 */
public class DbWriter {

    private Properties dbConnectionProperties;

    public DbWriter(Properties properties) {
        dbConnectionProperties = properties;
    }

    public void saveTextFileDetailesToDb(TextFile textFile) {
        SqlManager sqlManager = new SqlManager(dbConnectionProperties);
        String sqlQuery = "INSERT INTO file_statement VALUES ("
                + " NULL"
                + ", '" + textFile.getName() + "'"
                //use StingEscapeUtils for correct storing full file path in to DB
                + ", '" + StringEscapeUtils.escapeJava(textFile.getPath()) + "'"
                + ", '" + textFile.getLongestWord() + "'"
                + ", '" + textFile.getSmallestWord() + "'"
                + ", " + textFile.getLength()
                + ", " + textFile.getAvgWordLength()
                + ")";
        sqlManager.InsertOrUpdate(sqlQuery);
        sqlManager.Close();
        List<Line> lines = textFile.getLines();

        if (lines != null) {
            for (Line line : lines) {
                saveLineDetails(line);
            }
        }
    }

    private void saveLineDetails(Line line) {
        SqlManager sqlManager = new SqlManager(dbConnectionProperties);
        String sqlQuery = "INSERT INTO file_statement_breakdown VALUES ( "
                + " NULL"
                + ", (SELECT file_statement_id FROM file_statement ORDER BY file_statement_id DESC LIMIT 1)"
                + ", '" + line.getLineNumber() + "'"
                + ", '" + line.getText() + "'"
                + ", '" + line.getLongestWord() + "'"
                + ", '" + line.getSmallestWord() + "'"
                + ", " + line.getLength()
                + ", " + line.getAvgWordLength()
                + ")";
        sqlManager.InsertOrUpdate(sqlQuery);
        sqlManager.Close();
    }
}
