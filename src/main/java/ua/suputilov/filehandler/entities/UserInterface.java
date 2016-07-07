/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.suputilov.filehandler.entities;

import java.util.Scanner;
import java.util.logging.Logger;

/**
 *
 * @author sergey_putilov
 */
public class UserInterface {
    
    public final Logger LOG = Logger.getLogger(UserInterface.class.getName());
    public final String LINE_SEPARATOR = System.getProperty("line.separator");
    
    private String userAnswer;
    private Scanner scanner = new Scanner(System.in);
    
    /**
     * Returns the full path to folder or file.
     *
     * @param fullPathToFile
     * @return
     */
    public String askFullPathToFileOrFolder() {
        String fullPathToFile = "";
        LOG.info("Please, enter the full path to text file or folder: ");
        userAnswer = scanner.nextLine();

        // If entered line has character
        // that separates components of a file pat ("/" on UNIX and "\" on Windows).
        if (userAnswer.indexOf(System.getProperty("file.separator")) != -1) {
            fullPathToFile = userAnswer;
        }
        
        return fullPathToFile;
    }
}
