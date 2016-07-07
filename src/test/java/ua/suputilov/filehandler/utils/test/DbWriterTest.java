/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.suputilov.filehandler.utils.test;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import ua.suputilov.filehandler.entities.Line;
import ua.suputilov.filehandler.entities.TextFile;
import ua.suputilov.filehandler.utils.properties.PropertiesLoader;
import ua.suputilov.filehandler.utils.sql.DbWriter;

/**
 *
 * @author sergey_putilov
 */
public class DbWriterTest {

//    @Test
    public void saveTextFileDetailesToDbTest() throws Exception {
        System.out.println("-------------------------");
        System.out.println("saveTextFileDetailesToDbTest()");
        System.out.println("-------------------------");

        TextFile textFile = new TextFile();
        textFile.setName("Test");
        textFile.setPath("C:\\test\\Test.txt");
        textFile.setLongestWord("World");
        textFile.setSmallestWord("and");
        textFile.setLength(25);
        textFile.setAvgWordLength(3);
        List<Line> lines = new ArrayList<Line>();

        for (int i = 0; i < 10; i++) {
            Line line = new Line();
            line.setLineNumber(i);
            line.setText("Test line " + i);
            line.setLongestWord("Test");
            line.setSmallestWord("" + i);
            line.setLength(11);
            line.setAvgWordLength(3);
            lines.add(line);
        }
        textFile.setLines(lines);
        DbWriter dbWriter = new DbWriter(new PropertiesLoader("mysql_jdbc.properties").getProperties());
        dbWriter.saveTextFileDetailesToDb(textFile);

        System.out.println("TextFile name: " + textFile.getName());
    }
}
