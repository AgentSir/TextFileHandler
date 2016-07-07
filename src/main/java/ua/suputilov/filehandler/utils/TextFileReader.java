/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.suputilov.filehandler.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import ua.suputilov.filehandler.entities.TextFile;

/**
 * The class represents an object that reads all txt files from folder or txt
 * file depending of passed parameter to the constructor and creates a list of
 * processed TextFile objects. The constructor ready to get path to folder or
 * full path to txt file.
 *
 * @author sergey_putilov
 */
public class TextFileReader {

    private final Logger LOG = Logger.getLogger(TextFileReader.class.getName());

    private List<TextFile> textFiles;

    public TextFileReader(String path) {
        textFiles = new ArrayList<TextFile>();
        File file = new File(path);

        if (file.isDirectory()) {
            processTextFilesFromFolder(file);
        } else {
            processTextFile(file);
        }
    }

    /**
     * The method processes all txt files in folder recursively in each
     * subfolder. Each folder is processing in own thread.
     *
     * @param folder - The java.io.File object
     */
    public void processTextFilesFromFolder(File folder) {
        File[] folderEntries = folder.listFiles();
        List<Thread> threads = new ArrayList<Thread>();

        for (final File entry : folderEntries) {

            if (entry.isDirectory()) {

                // create new Thread to prosess subfolder
                threads.add(new Thread(new Runnable() {
                    @Override
                    public void run() {
                        processTextFilesFromFolder(entry);
                    }
                }));

                continue;
            }
            String path = entry.getPath();

            if (isTextFile(path)) {
                TextFileInitializer textFileInitializer = new TextFileInitializer(entry.getPath());
                textFiles.add(textFileInitializer.getTextFile());
            } else {
                LOG.info("The file \"" + entry.getName() + "\" was skipped because it isn't txt file.");
            }
        }

        // run all created threads and wait for to finished all
        if (threads != null) {

            for (Thread thread : threads) {
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                }
            }
        }
    }

    /**
     * The method processes txt file.
     *
     * @param file - The java.io.File object
     */
    public void processTextFile(File file) {
        String path = file.getPath();

        if (isTextFile(path)) {
            TextFileInitializer textFileInitializer = new TextFileInitializer(file.getPath());
            textFiles.add(textFileInitializer.getTextFile());
        } else {
            LOG.info("The file \"" + file.getName() + "\" was skipped because it isn't txt file.");
        }
    }

    /**
     * The method use regular expression to define is there file has .txt
     * extension.
     *
     * @param path
     * @return boolean
     */
    private boolean isTextFile(String path) {

        return path.matches("(.*).(txt)");
    }

    public List<TextFile> getTextFiles() {

        return textFiles;
    }

    public void setTextFiles(List<TextFile> textFiles) {
        this.textFiles = textFiles;
    }
}
