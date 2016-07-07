/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.suputilov.filehandler.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import ua.suputilov.filehandler.entities.Line;
import ua.suputilov.filehandler.entities.TextFile;

/**
 * The class TextFileInitializer represents an object that is initializing all
 * TextFile object's properties.
 *
 * @author sergey_putilov
 */
public class TextFileInitializer {

    private static final String DIR_SEPARATOR = System.getProperty("file.separator");
    private final Logger LOG = Logger.getLogger(TextFileInitializer.class.getName());

    private String fullPathToFile;
    private TextFile textFile;

    public TextFileInitializer(String fullPathToFile) {
        this.fullPathToFile = fullPathToFile;
        textFile = new TextFile();

        initializeTextFile();
    }

    public TextFile getTextFile() {
        return textFile;
    }

    private void initializeTextFile() {
        Handler handler = new Handler();
        textFile.setName(getFileName());
        textFile.setPath(fullPathToFile);
        textFile.setText(getFullText(fullPathToFile));
        textFile.setLines(getListOfLines(fullPathToFile));
        textFile.setLongestWord(handler.findLongestWord(textFile.getText()));
        textFile.setSmallestWord(handler.findSmallestWord(textFile.getText()));
        textFile.setLength(handler.calculateLength(textFile.getText()));
        textFile.setAvgWordLength(handler.calculateAverageWordLength(textFile.getText()));
    }

    private String getFileName() {
        String fileName = fullPathToFile.substring(fullPathToFile.lastIndexOf(DIR_SEPARATOR) + 1);

        return fileName;
    }

    /**
     * The method returns string with full text from the file
     *
     * @param fullPathToFile
     * @return String - text
     */
    public String getFullText(String fullPathToFile) {
        String text = "";
        List<Line> lines = getListOfLines(fullPathToFile);

        if (lines != null) {
            for (Line line : lines) {
                text = text.concat(line.getText());
                text = text.concat(" ");
            }
        }

        return text;
    }

    /**
     * The method reads the text file and returns a list of objects. The each
     * element in the list it's the Line object. Every line in the txt file is
     * processing in own thread.
     *
     * @param fullPathToFile
     * @return List<Line>
     */
    public List<Line> getListOfLines(String fullPathToFile) {
        BufferedReader br = null;
        final List<Line> lines = new ArrayList<Line>();
        List<Thread> threads = new ArrayList<Thread>();

        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(fullPathToFile));
            int currentlineNumber = 0;

            while ((sCurrentLine = br.readLine()) != null) {
                currentlineNumber++;
                final int lineNumber = currentlineNumber;
                final String lineText = sCurrentLine;

                // create new Thread to prosess line
                threads.add(new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // initialize and add to list the Line object
                        lines.add(new LineInitializer(lineText, lineNumber).getLine());
                    }
                }));
            }
        } catch (IOException e) {
            LOG.info(e.toString());
        } finally {
            try {

                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                LOG.info(ex.toString());
            }
        }

        // run all created threads and wait for finished all
        if (threads != null) {

            for (Thread thread : threads) {
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                }
            }
        }

        return lines;
    }
}
