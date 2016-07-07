/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.suputilov.filehandler.entities;

import java.util.List;

/**
 * The class TextFile represents a txt file.
 *
 * @author sergey_putilov
 */
public class TextFile extends Text {

    private String name;
    private String path;
    private List<Line> lines;

    public String getName() {
        
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Line> getLines() {
        
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }
}
