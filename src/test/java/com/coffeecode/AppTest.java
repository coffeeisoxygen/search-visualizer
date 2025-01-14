package com.coffeecode;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.coffeecode.exception.DictionaryLoadException;
import com.coffeecode.load.ILoadAble;
import com.coffeecode.model.DictionaryData;

class AppTest {
    @Mock
    private ILoadAble mockLoader;
    
    @Mock
    private DictionaryData mockDictionary;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSuccessfulStartup() {
        when(mockLoader.loadBothDictionaries(anyString(), anyString()))
            .thenReturn(mockDictionary);
        when(mockDictionary.size()).thenReturn(5);

        assertDoesNotThrow(() -> {
            App.main(new String[]{});
        });
    }

    @Test
    void testDictionaryLoadError() {
        when(mockLoader.loadBothDictionaries(anyString(), anyString()))
            .thenThrow(new DictionaryLoadException("Test error"));

        assertDoesNotThrow(() -> {
            App.main(new String[]{});
        });
    }
}
