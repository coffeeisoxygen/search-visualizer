package com.coffeecode.search;

import java.util.List;

public abstract class AbstractSearch implements ISearchable {
    protected SearchResult createResult(List<Integer> steps, int foundIndex, long startTime) {
        long duration = System.currentTimeMillis() - startTime;
        return new SearchResult(steps, foundIndex, duration);
    }
}