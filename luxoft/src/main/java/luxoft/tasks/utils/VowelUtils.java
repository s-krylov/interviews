package luxoft.tasks.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Helper class. It provides utility methods to work with vowel in word.
 */
public class VowelUtils {
    /**
     * The vowel letters of the English alphabet
     * @see <a href="https://simple.wikipedia.org/wiki/Vowel">wikipedia</a>
     */
    private static final Set<Character> VOWELS = new HashSet<>(Arrays.asList('A', 'E', 'I', 'O', 'U', 'Y'));

    private VowelUtils() {}

    /**
     * Get vowels set from passed string {@code source}
     * @param source string
     * @return vowels set
     */
    public static Set<Character> getVowelsFrom(String source) {
        Set<Character> result = new HashSet<>();
        for (char c : source.toCharArray()) {
            if (VOWELS.contains(Character.valueOf(c))) {
                result.add(c);
            }
        }
        return result;
    }

    /**
     * Get vowels count from passed string {@code source}
     * @param source string
     * @return vowels count
     */
    public static int countVowelsIn(String source) {
        int result = 0;
        for (char c : source.toCharArray()) {
            if (VOWELS.contains(Character.valueOf(c))) {
                result ++;
            }
        }
        return result;
    }
}
