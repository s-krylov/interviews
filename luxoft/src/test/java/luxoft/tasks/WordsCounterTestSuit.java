package luxoft.tasks;

import luxoft.tasks.model.LettersSummaryInfo;
import luxoft.tasks.model.VowelsInWord;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.Assert.*;

public class WordsCounterTestSuit {

    @Test
    public void testReadData() throws Exception {
        Map<VowelsInWord, LettersSummaryInfo> expected = new HashMap<>();
        expected.put(new VowelsInWord("Platon"), new LettersSummaryInfo().addInfo("Platon").addInfo("bamboo"));
        expected.put(new VowelsInWord("made"), new LettersSummaryInfo().addInfo("made"));
        expected.put(new VowelsInWord("boats"), new LettersSummaryInfo().addInfo("boats"));
        Map<VowelsInWord, LettersSummaryInfo> map = new HashMap<>();
        Method method = WordsCounter.class.getDeclaredMethod("readData", Scanner.class, Map.class);
        method.setAccessible(true);
        try (Scanner scanner = new Scanner(WordsCounterTestSuit.class.getResourceAsStream("/INPUT.TXT"))) {
            method.invoke(null, scanner, map);
        }
        assertEquals(expected, map);
    }

    @Test
    public void testWriteData() throws Exception {
        Map<VowelsInWord, LettersSummaryInfo> map = new HashMap<>();
        map.put(new VowelsInWord("Platon"), new LettersSummaryInfo().addInfo("Platon").addInfo("bamboo"));
        map.put(new VowelsInWord("made"), new LettersSummaryInfo().addInfo("made"));
        map.put(new VowelsInWord("boats"), new LettersSummaryInfo().addInfo("boats"));
        Method method = WordsCounter.class.getDeclaredMethod("writeData", PrintWriter.class, Map.class);
        method.setAccessible(true);
        StringWriter stringWriter = new StringWriter();
        try (PrintWriter pw = new PrintWriter(stringWriter)) {
            method.invoke(null, pw, map);
        }
        assertEquals(String.format("([A, O], 5) -> 2.0%n" +
                "([A, O], 6) -> 2.5%n" +
                "([A, E], 4) -> 2.0%n"), stringWriter.toString());
    }
}
