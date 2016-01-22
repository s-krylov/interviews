package luxoft.tasks.model;

import luxoft.tasks.utils.VowelUtils;

import java.util.Objects;
import java.util.Set;

/**
 * Information about vowel in words. Immutable data structure
 */
public class VowelsInWord {

    /**
     * Word's vowel
     */
    private Set<Character> vowels;
    /**
     * Word's length
     */
    private int length;

    public VowelsInWord(String word) {
        word = word.toUpperCase();
        length = word.length();
        vowels = VowelUtils.getVowelsFrom(word);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VowelsInWord that = (VowelsInWord) o;
        return Objects.equals(length, that.length) &&
                Objects.equals(vowels, that.vowels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vowels, length);
    }

    @Override
    public String toString() {
        return "(" + vowels + ", " + length + ')';
    }
}
