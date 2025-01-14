package com.coffeecode.service.search;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class LinearSearchTest extends SearchTestBase {
    private final LinearSearch search = new LinearSearch();

    @Test
    void testFoundElement() {
        search.setObserver(observer);
        var result = search.search(sortedData, "kucing");

        assertTrue(result.isFound());
        assertEquals(2, result.getFoundIndex());
        assertEquals(3, result.getSteps().size());
        assertEquals(3, observer.getStates().size());
    }

    @Test
    void testNotFound() {
        var result = search.search(sortedData, "zebra");
        assertFalse(result.isFound());
        assertEquals(sortedData.length, result.getSteps().size());
    }
}
