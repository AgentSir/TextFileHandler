/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.suputilov.filehandler.utils.test;

import org.junit.*;
import static org.junit.Assert.assertEquals;

import ua.suputilov.filehandler.utils.Handler;

/**
 *
 * @author sergey_putilov
 */
public class HandlerTest {

    @Test
    public void findLongestWordTest() throws Exception {

        System.out.println("-------------------------");
        System.out.println("findLongestWordTest()");
        System.out.println("-------------------------");

        Handler handler = new Handler();
        String longestWord = handler.findLongestWord("Hello world ");

        assertEquals(longestWord, "Hello");
    }

    @Test
    public void findSmallestWordTest() throws Exception {

        System.out.println("-------------------------");
        System.out.println("findSmallestWordTest()");
        System.out.println("-------------------------");

        Handler handler = new Handler();
        String smallestWord = handler.findSmallestWord("Hello world from application");

        assertEquals(smallestWord, "from");
    }

    @Test
    public void calculateLengthTest() throws Exception {

        System.out.println("-------------------------");
        System.out.println("calculateLengthTest()");
        System.out.println("-------------------------");

        Handler handler = new Handler();
        int textLength = handler.calculateLength("Hello world from application");

        assertEquals(textLength, 28);
    }

    @Test
    public void calculateAvgWordLengthTest() throws Exception {

        System.out.println("-------------------------");
        System.out.println("calculateAvgWordLengthTest()");
        System.out.println("-------------------------");

        Handler handler = new Handler();
        int avarageLenghtOfWord = handler.calculateAverageWordLength("Hello world from application");

        assertEquals(avarageLenghtOfWord, 6);
    }
}
