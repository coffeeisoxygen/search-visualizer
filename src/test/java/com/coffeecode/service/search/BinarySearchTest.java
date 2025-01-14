package com.coffeecode.service.search;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class BinarySearchTest extends SearchTestBase {
    private final BinarySearch search = new BinarySearch();

    @Test
    void testFoundElement() {
        search.setObserver(observer);
        var result = search.search(sortedData, "kucing");
        
        assertTrue(result.isFound());
        assertEquals(2, result.getFoundIndex());
        assertTrue(result.getSteps().size() <= 2);
        assertTrue(observer.getStates().size() <= 2);
    }

    @Test
    void testNotFound() {
        var result = search.search(sortedData, "zebra");
        assertFalse(result.isFound());
        assertTrue(result.getSteps().size() <= 3);
    }
}
