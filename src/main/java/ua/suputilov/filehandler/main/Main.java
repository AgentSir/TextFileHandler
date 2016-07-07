/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.suputilov.filehandler.main;

import java.util.List;
import java.util.Properties;
import ua.suputilov.filehandler.entities.TextFile;
import ua.suputilov.filehandler.entities.UserInterface;
import ua.suputilov.filehandler.utils.TextFileReader;
import ua.suputilov.filehandler.utils.properties.PropertiesLoader;
import ua.suputilov.filehandler.utils.sql.DbWriter;

/**
 * The Main class.
 *
 * @author sergey_putilov
 */
public class Main {

    // Names of properties files.
    private static final String DB_CONNECTION_PROPERTIES_FILE_NAME = "mysql_jdbc.properties";

    public static void main(String[] args) {
        // Create the userInterface for communicating with the user.
        UserInterface userInterface = new UserInterface();
        // Set up default DB connection properties from mysql_jdbs.properties file
        Properties dbProperties = new PropertiesLoader(DB_CONNECTION_PROPERTIES_FILE_NAME).getProperties();
        // Ask user to enter full path to txt file and get answer.
        String fullPathToTextFile = userInterface.askFullPathToFileOrFolder();
        // Initilizing the TextFileReader object to handle all txt files.
        TextFileReader textFileReader = new TextFileReader(fullPathToTextFile);
        // Getting a list of initialized TextFile objects
        List<TextFile> textFiles = textFileReader.getTextFiles();
        // Saving the TextFile entities to the DB.
        DbWriter dbWriter = new DbWriter(dbProperties);

        if (textFiles != null) {
            for (TextFile textFile : textFiles) {
                dbWriter.saveTextFileDetailesToDb(textFile);
            }
        }
    }
}
