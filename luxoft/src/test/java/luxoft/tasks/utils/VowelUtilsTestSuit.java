package luxoft.tasks.utils;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

public class VowelUtilsTestSuit {

    @Test
    public void testGetVowelsFrom() {
        assertEquals(new HashSet<>(Arrays.asList('A', 'O')), VowelUtils.getVowelsFrom("PLATON"));
        assertEquals(new HashSet<>(Arrays.asList('A', 'E')), VowelUtils.getVowelsFrom("MADE"));
        assertNotEquals(Arrays.asList('A', 'O', 'O'), VowelUtils.getVowelsFrom("BAMBOO"));
        assertEquals(new HashSet<>(Arrays.asList('A', 'O')), VowelUtils.getVowelsFrom("BAMBOO"));
        assertEquals(new HashSet<>(Arrays.asList('A', 'O')), VowelUtils.getVowelsFrom("BOATS"));
        assertNotEquals(Arrays.asList('O', 'U', 'E', 'E'), VowelUtils.getVowelsFrom("OCCURRENCES"));
        assertNotEquals(Arrays.asList('O', 'U', 'S'), VowelUtils.getVowelsFrom("OCCURRENCES"));
        assertNotEquals(Arrays.asList('O', 'U', 'Y'), VowelUtils.getVowelsFrom("OCCURRENCES"));
        assertEquals(new HashSet<>(Arrays.asList('O', 'U', 'E')), VowelUtils.getVowelsFrom("OCCURRENCES"));
    }

    @Test
    public void testCountVowelsIn() {
        assertEquals(2, VowelUtils.countVowelsIn("PLATON"));
        assertEquals(2, VowelUtils.countVowelsIn("MADE"));
        assertEquals(3, VowelUtils.countVowelsIn("BAMBOO"));
        assertNotEquals(2, VowelUtils.countVowelsIn("BAMBOO"));
        assertEquals(2, VowelUtils.countVowelsIn("BOATS"));
        assertEquals(4, VowelUtils.countVowelsIn("OCCURRENCES"));
        assertNotEquals(3, VowelUtils.countVowelsIn("OCCURRENCES"));
    }
}
