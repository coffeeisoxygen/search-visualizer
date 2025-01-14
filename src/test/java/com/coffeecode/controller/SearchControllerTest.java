package com.coffeecode.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.coffeecode.model.SearchResult;

class SearchControllerTest {
    private SearchController controller;
    private String[] testData;

    @BeforeEach
    void setUp() {
        controller = new SearchController();
        testData = new String[]{"anjing", "burung", "kucing"};
    }

    @Test
    void testDefaultSearch() {
        SearchResult result = controller.search(testData, "kucing");
        assertTrue(result.isFound());
        assertEquals(2, result.getFoundIndex());
    }

    @Test
    void testSwitchStrategy() {
        controller.setSearchStrategy(true); // Switch to binary
        SearchResult result = controller.search(testData, "kucing");
        assertTrue(result.isFound());
        assertEquals(2, result.getFoundIndex());
        assertTrue(result.getSteps().size() <= 2);
    }
}
