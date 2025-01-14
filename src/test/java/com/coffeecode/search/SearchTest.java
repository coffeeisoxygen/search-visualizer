package com.coffeecode.search;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.coffeecode.model.SearchResult;
import com.coffeecode.service.search.BinarySearch;
import com.coffeecode.service.search.LinearSearch;

class SearchTest {
    private String[] sortedData;
    private String[] singleData;
    private String[] emptyData;
    private String[] largeData;
    private LinearSearch linearSearch;
    private BinarySearch binarySearch;

    @BeforeEach
    void setUp() {
        sortedData = new String[] { "anjing", "burung", "kucing" };
        singleData = new String[] { "anjing" };
        emptyData = new String[] {};
        linearSearch = new LinearSearch();
        binarySearch = new BinarySearch();

        // Create large sorted dataset
        largeData = IntStream.range(0, 10000)
                .mapToObj(String::valueOf)
                .toArray(String[]::new);
    }

    @Test
    void testLinearSearchFound() {
        SearchResult result = linearSearch.search(sortedData, "kucing");
        assertTrue(result.isFound());
        assertEquals(2, result.getFoundIndex());
        assertEquals(3, result.getSteps().size());
        assertTrue(result.getDuration() >= 0);
    }

    @Test
    void testBinarySearchFound() {
        SearchResult result = binarySearch.search(sortedData, "kucing");
        assertTrue(result.isFound());
        assertEquals(2, result.getFoundIndex());
        assertTrue(result.getSteps().size() <= 2);
        assertTrue(result.getDuration() >= 0);
    }

    @Test
    void testSearchNotFound() {
        assertFalse(linearSearch.search(sortedData, "zebra").isFound());
        assertFalse(binarySearch.search(sortedData, "zebra").isFound());
    }

    @Test
    void testEmptyArray() {
        assertFalse(linearSearch.search(emptyData, "any").isFound());
        assertFalse(binarySearch.search(emptyData, "any").isFound());
    }

    @Test
    void testSingleElement() {
        assertTrue(linearSearch.search(singleData, "anjing").isFound());
        assertTrue(binarySearch.search(singleData, "anjing").isFound());
        assertEquals(0, linearSearch.search(singleData, "anjing").getFoundIndex());
        assertEquals(0, binarySearch.search(singleData, "anjing").getFoundIndex());
    }

    @Test
    void testSearchSteps() {
        SearchResult linearResult = linearSearch.search(sortedData, "kucing");
        SearchResult binaryResult = binarySearch.search(sortedData, "kucing");

        List<Integer> linearSteps = linearResult.getSteps();
        List<Integer> binarySteps = binaryResult.getSteps();

        assertEquals(List.of(0, 1, 2), linearSteps);
        assertTrue(binarySteps.size() <= Math.ceil(Math.log(sortedData.length) / Math.log(2)) + 1);
    }

    @Test
    void testSearchPerformance() {
        // Best case
        long linearStartBest = System.nanoTime();
        SearchResult linearBest = linearSearch.search(largeData, "0");
        long linearBestTime = System.nanoTime() - linearStartBest;

        long binaryStartBest = System.nanoTime();
        SearchResult binaryBest = binarySearch.search(largeData, "0");
        long binaryBestTime = System.nanoTime() - binaryStartBest;

        // Worst case
        long linearStartWorst = System.nanoTime();
        SearchResult linearWorst = linearSearch.search(largeData, "9999");
        long linearWorstTime = System.nanoTime() - linearStartWorst;

        long binaryStartWorst = System.nanoTime();
        SearchResult binaryWorst = binarySearch.search(largeData, "9999");
        long binaryWorstTime = System.nanoTime() - binaryStartWorst;

        // Middle case
        long linearStartMid = System.nanoTime();
        SearchResult linearMid = linearSearch.search(largeData, "5000");
        long linearMidTime = System.nanoTime() - linearStartMid;

        long binaryStartMid = System.nanoTime();
        SearchResult binaryMid = binarySearch.search(largeData, "5000");
        long binaryMidTime = System.nanoTime() - binaryStartMid;

        // Verify results
        assertTrue(linearBest.isFound() && binaryBest.isFound());
        assertTrue(linearWorst.isFound() && binaryWorst.isFound());
        assertTrue(linearMid.isFound() && binaryMid.isFound());

        // Verify performance characteristics
        assertTrue(binaryBestTime <= linearBestTime * 2); // Allow some overhead
        assertTrue(binaryWorstTime <= linearWorstTime);
        assertTrue(binaryMidTime <= linearMidTime);

        // Verify steps count
        assertTrue(binaryBest.getSteps().size() <= Math.ceil(Math.log(largeData.length) / Math.log(2)));
        assertEquals(largeData.length, linearWorst.getSteps().size());
    }

    @Test
    void testNullInput() {
        Exception dataException = assertThrows(IllegalArgumentException.class,
                () -> linearSearch.search(null, "test"));
        assertEquals("Data array cannot be null", dataException.getMessage());

        Exception targetException = assertThrows(IllegalArgumentException.class,
                () -> linearSearch.search(sortedData, null));
        assertEquals("Search target cannot be null", targetException.getMessage());

        dataException = assertThrows(IllegalArgumentException.class,
                () -> binarySearch.search(null, "test"));
        assertEquals("Data array cannot be null", dataException.getMessage());

        targetException = assertThrows(IllegalArgumentException.class,
                () -> binarySearch.search(sortedData, null));
        assertEquals("Search target cannot be null", targetException.getMessage());
    }

    @Test
    void testCaseSensitivity() {
        assertFalse(linearSearch.search(sortedData, "KUCING").isFound());
        assertFalse(binarySearch.search(sortedData, "KUCING").isFound());
    }

    @Test
    void testSpecialCharacters() {
        String[] specialData = { "$test", "@test", "[test]" };
        assertTrue(linearSearch.search(specialData, "$test").isFound());
        assertTrue(binarySearch.search(specialData, "$test").isFound());
    }
}