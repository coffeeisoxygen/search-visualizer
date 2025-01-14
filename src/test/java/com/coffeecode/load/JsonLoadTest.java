package com.coffeecode.load;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.coffeecode.model.DictionaryData;

class JsonLoadTest {
    private JsonLoad jsonLoad;
    private static final String TEST_DICTIONARY_PATH = "src/test/resources/data/test-dictionary.json";

    @BeforeEach
    void setUp() {
        jsonLoad = new JsonLoad();
    }

    @Test
    void testLoadDictionary() {
        DictionaryData dictionary = jsonLoad.load(TEST_DICTIONARY_PATH);

        assertNotNull(dictionary);
        assertEquals(3, dictionary.size());
        assertEquals("cat", dictionary.getIndToEng().get("kucing"));
        assertEquals("kucing", dictionary.getEngToInd().get("cat"));
    }
}