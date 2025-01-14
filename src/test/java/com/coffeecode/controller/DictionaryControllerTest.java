package com.coffeecode.controller;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.coffeecode.service.dictionary.IDictionaryService;

class DictionaryControllerTest {
    @Mock
    private IDictionaryService dictionaryService;
    private DictionaryController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new DictionaryController(dictionaryService);
    }

    @Test
    void testTranslate() {
        when(dictionaryService.translate("kucing", true))
            .thenReturn("cat");
        assertEquals("cat", controller.translate("kucing", true));
    }

    @Test
    void testGetWords() {
        String[] indoWords = {"anjing", "kucing"};
        when(dictionaryService.getIndoWords()).thenReturn(indoWords);
        assertArrayEquals(indoWords, controller.getWords(true));
    }
}
