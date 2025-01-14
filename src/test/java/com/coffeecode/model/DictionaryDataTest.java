package com.coffeecode.model;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DictionaryDataTest {
    private DictionaryData dictionary;
    private Map<String, String> indToEng;
    private Map<String, String> engToInd;

    @BeforeEach
    void setUp() {
        indToEng = new HashMap<>();
        indToEng.put("kucing", "cat");
        indToEng.put("anjing", "dog");
        indToEng.put("burung", "bird");

        engToInd = new HashMap<>();
        engToInd.put("cat", "kucing");
        engToInd.put("dog", "anjing");
        engToInd.put("bird", "burung");

        dictionary = new DictionaryData(indToEng, engToInd);
    }

    @Test
    void testSortedData() {
        String[] expectedIndoWords = { "anjing", "burung", "kucing" };
        String[] expectedEngWords = { "bird", "cat", "dog" };

        assertArrayEquals(expectedIndoWords, dictionary.getIndoWords());
        assertArrayEquals(expectedEngWords, dictionary.getEngWords());
    }

    @Test
    void testUnmodifiableMaps() {
        assertThrows(UnsupportedOperationException.class,
                () -> dictionary.getIndToEng().put("test", "test"));
        assertThrows(UnsupportedOperationException.class,
                () -> dictionary.getEngToInd().put("test", "test"));
    }

    @Test
    void testSize() {
        assertEquals(3, dictionary.size());
    }

    @Test
    void testIsEmpty() {
        assertFalse(dictionary.isEmpty());
        dictionary = new DictionaryData(new HashMap<>(), new HashMap<>());
        assertTrue(dictionary.isEmpty());
    }
}
