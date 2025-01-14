package com.coffeecode.search;

import java.util.List;

public abstract class AbstractSearch implements ISearchable {
    protected SearchObserver observer;
    
    @Override
    public void setObserver(SearchObserver observer) {
        this.observer = observer;
    }
    
    protected void notifyStep(SearchState state) {
        if (observer != null) {
            observer.onSearchStep(state);
        }
    }
    
    protected void notifyComplete(SearchResult result) {
        if (observer != null) {
            observer.onSearchComplete(result);
        }
    }

    protected void validateInput(String[] data, String target) {
        if (data == null) {
            throw new IllegalArgumentException("Data array cannot be null");
        }
        if (target == null) {
            throw new IllegalArgumentException("Search target cannot be null");
        }
    }

    protected SearchResult createResult(List<Integer> steps, int foundIndex, long startTime) {
        return new SearchResult(steps, foundIndex, System.nanoTime() - startTime);
    }
}