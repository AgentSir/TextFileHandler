/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.suputilov.filehandler.utils;

import ua.suputilov.filehandler.entities.Line;

/**
 * The class LineInitializer represents an object that is initializing all Line
 * object's properties.
 *
 * @author sergey_putilov
 */
public class LineInitializer {

    private Line line;

    public LineInitializer(String line, int lineNumber) {
        this.line = new Line();
        initializeLine(line, lineNumber);
    }

    public Line getLine() {
        
        return this.line;
    }

    private void initializeLine(String text, int lineNumber) {
        Handler handler = new Handler();
        this.line.setText(text);
        this.line.setLineNumber(lineNumber);
        this.line.setLongestWord(handler.findLongestWord(text));
        this.line.setSmallestWord(handler.findSmallestWord(text));
        this.line.setLength(handler.calculateLength(text));
        this.line.setAvgWordLength(handler.calculateAverageWordLength(text));
    }
}
