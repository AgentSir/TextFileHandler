/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.suputilov.filehandler.utils.properties;

import java.io.*;

import java.util.Properties;
import java.util.logging.Logger;

/**
 * The PropertiesLoader class represents utils for load the properties from
 * properties file.
 *
 * @author sergey_putilov
 */
public class PropertiesLoader {

    private final Logger LOG = Logger.getLogger(PropertiesLoader.class.getName());

    private Properties properties = new Properties();

    public PropertiesLoader(String propertiesFileName) {
        setProperties(propertiesFileName);
    }

    public Properties getProperties() {
        
        return properties;
    }

    /**
     * Loads the properties.
     */
    private void setProperties(String propertiesFileName) {
        InputStream inputStream;
        Reader readerIS;

        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName);
            readerIS = new InputStreamReader(inputStream, "UTF-8");
            properties.load(readerIS);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
