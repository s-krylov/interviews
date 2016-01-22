package luxoft.tasks.model;

import luxoft.tasks.utils.VowelUtils;

import java.util.Objects;

/**
 * Summary information about total vowel letters and word count
 */
public class LettersSummaryInfo {

    /**
     * Words count
     */
    private int words;
    /**
     * Vowels count
     */
    private int vowels;

    public LettersSummaryInfo addInfo(String word) {
        words ++;
        vowels += VowelUtils.countVowelsIn(word.toUpperCase());
        return this;
    }

    public int getWords() {
        return words;
    }

    public int getVowels() {
        return vowels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LettersSummaryInfo that = (LettersSummaryInfo) o;
        return Objects.equals(words, that.words) &&
                Objects.equals(vowels, that.vowels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(words, vowels);
    }
}
