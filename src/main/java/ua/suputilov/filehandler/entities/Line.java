/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.suputilov.filehandler.entities;

/**
 * The class Line represents one line of text file.
 *
 * @author sergey_putilov
 */
public class Line extends Text {

    private int lineNumber;

    public int getLineNumber() {

        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
}
