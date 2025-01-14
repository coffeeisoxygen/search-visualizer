package com.coffeecode.search;

import java.util.List;

public abstract class AbstractSearch implements ISearchable {
    protected void validateInput(String[] data, String target) {
        if (data == null) {
            throw new IllegalArgumentException("Data array cannot be null");
        }
        if (target == null) {
            throw new IllegalArgumentException("Search target cannot be null");
        }
    }

    protected SearchResult createResult(List<Integer> steps, int foundIndex, long startTime) {
        long duration = System.nanoTime() - startTime;
        return new SearchResult(steps, foundIndex, duration);
    }
}