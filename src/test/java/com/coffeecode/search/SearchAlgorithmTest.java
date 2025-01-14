package com.coffeecode.search;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.coffeecode.model.SearchResult;
import com.coffeecode.service.search.BinarySearch;
import com.coffeecode.service.search.LinearSearch;
import com.coffeecode.service.search.SearchObserver;
import com.coffeecode.service.search.SearchState;

class SearchAlgorithmTest {
    private String[] sortedData;
    private LinearSearch linearSearch;
    private BinarySearch binarySearch;
    private TestSearchObserver observer;

    @BeforeEach
    void setUp() {
        sortedData = new String[]{"anjing", "burung", "kucing", "singa"};
        linearSearch = new LinearSearch();
        binarySearch = new BinarySearch();
        observer = new TestSearchObserver();
        linearSearch.setObserver(observer);
        binarySearch.setObserver(observer);
    }

    @Test
    void testLinearSearch() {
        SearchResult result = linearSearch.search(sortedData, "kucing");
        assertTrue(result.isFound());
        assertEquals(2, result.getFoundIndex());
        assertEquals(3, result.getSteps().size());
        assertEquals(3, observer.getStepCount());
    }

    @Test
    void testBinarySearch() {
        SearchResult result = binarySearch.search(sortedData, "kucing");
        assertTrue(result.isFound());
        assertEquals(2, result.getFoundIndex());
        assertTrue(result.getSteps().size() <= 2);
        assertTrue(observer.getStepCount() <= 2);
    }

    @Test
    void testSearchNotFound() {
        assertFalse(linearSearch.search(sortedData, "zebra").isFound());
        assertFalse(binarySearch.search(sortedData, "zebra").isFound());
    }

    private static class TestSearchObserver implements SearchObserver {
        private int stepCount = 0;
        private SearchResult lastResult;

        @Override
        public void onSearchStep(SearchState state) {
            stepCount++;
        }

        @Override
        public void onSearchComplete(SearchResult result) {
            lastResult = result;
        }

        public int getStepCount() {
            return stepCount;
        }

        public SearchResult getLastResult() {
            return lastResult;
        }
    }
}