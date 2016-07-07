/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.suputilov.filehandler.utils;

/**
 * The class Handler represents an object that able to find and calculate text
 * details like longest word, smallest word, text length, average word length.
 *
 * @author sergey_putilov
 */
public class Handler {

    /**
     * The method finds longest word in the text.
     *
     * @param text
     * @return String
     */
    public String findLongestWord(String text) {
        String[] words = text.split(" ");
        int maxLength = 0;
        String longestWord = "";

        if (words != null) {
            for (String word : words) {
                if (word.length() > maxLength) {
                    maxLength = word.length();
                    longestWord = word;
                }
            }
        }

        return longestWord;
    }

    /**
     * The method finds smallest word in the text.
     *
     * @param text
     * @return String
     */
    public String findSmallestWord(String text) {
        String[] words = text.split(" ");
        int minLength = Integer.MAX_VALUE;
        String smallestWord = "";

        if (words != null) {
            for (String word : words) {
                if (word.length() < minLength) {
                    minLength = word.length();
                    smallestWord = word;
                }
            }
        }

        return smallestWord;
    }

    /**
     * The method calculates text length.
     *
     * @param text
     * @return int
     */
    public int calculateLength(String text) {

        return text.length();
    }

    /**
     * The method calculates average word length in the text.
     *
     * @param text
     * @return int
     */
    public int calculateAverageWordLength(String text) {
        String[] words = text.split(" ");
        int countWords = words.length;
        int countCharacters = 0;

        for (int i = 0; i < countWords; i++) {
            countCharacters = countCharacters + words[i].length();
        }

        return countCharacters / countWords;
    }
}
