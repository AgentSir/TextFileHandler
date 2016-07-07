/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.suputilov.filehandler.entities;

/**
 * The class Text represents the text and conteins properties which represents
 * its statistics.
 *
 * @author sergey_putilov
 */
public abstract class Text {

    private String text;
    private String longestWord;
    private String smallestWord;
    private int length;
    private int avgWordLength;

    public String getText() {

        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLongestWord() {

        return longestWord;
    }

    public void setLongestWord(String longestWord) {
        this.longestWord = longestWord;
    }

    public String getSmallestWord() {

        return smallestWord;
    }

    public void setSmallestWord(String smallestWord) {
        this.smallestWord = smallestWord;
    }

    public int getLength() {

        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getAvgWordLength() {

        return avgWordLength;
    }

    public void setAvgWordLength(int avgWordLength) {
        this.avgWordLength = avgWordLength;
    }
}
