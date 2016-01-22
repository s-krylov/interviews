package luxoft.tasks;

import luxoft.tasks.model.LettersSummaryInfo;
import luxoft.tasks.model.VowelsInWord;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.util.Map.Entry;

/**
 * Entry point
 */
public class WordsCounter {

    /**
     * Input file name
     */
    private static final String INPUT_FILE_NAME = "INPUT.TXT";
    /**
     * Output file name
     */
    private static final String OUTPUT_FILE_NAME = "OUTPUT.TXT";

    public static void main(String [] args) {
        Map<VowelsInWord, LettersSummaryInfo> words2Summary = new HashMap<>();
        try (Scanner scanner = new Scanner(new FileInputStream(new File(INPUT_FILE_NAME)))) {
            readData(scanner, words2Summary);
        } catch (FileNotFoundException fnfe) {
            System.out.println("File " + INPUT_FILE_NAME + " not found");
            return;
        }
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OUTPUT_FILE_NAME))))) {
            writeData(writer, words2Summary);
        } catch (FileNotFoundException fnfe) {
            System.out.println("Cant create " + OUTPUT_FILE_NAME);
        }
    }

    /**
     * Load data from scanner to map
     * @param scanner scanner
     * @param dest map (words, summary)
     */
    private static void readData(Scanner scanner, Map<VowelsInWord, LettersSummaryInfo> dest) {
        while (scanner.hasNext()) {
            String word = scanner.next();
            VowelsInWord vowelsInWord = new VowelsInWord(word);
            LettersSummaryInfo summaryInfo = dest.get(vowelsInWord);
            if (summaryInfo == null) {
                summaryInfo = new LettersSummaryInfo();
                dest.put(vowelsInWord, summaryInfo);
            }
            summaryInfo.addInfo(word);
        }
    }

    /**
     * Writes data from source map to writer
     * @param writer writer
     * @param src source map
     */
    private static void writeData(PrintWriter writer, Map<VowelsInWord, LettersSummaryInfo> src) {
        for (Entry<VowelsInWord, LettersSummaryInfo> entry : src.entrySet()) {
            VowelsInWord vowelsInWord = entry.getKey();
            LettersSummaryInfo summaryInfo = entry.getValue();
            writer.println(vowelsInWord + " -> " + 1.0 * summaryInfo.getVowels() / summaryInfo.getWords());
        }
    }
}
