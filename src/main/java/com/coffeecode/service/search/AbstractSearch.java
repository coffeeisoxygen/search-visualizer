package com.coffeecode.service.search;

import com.coffeecode.model.SearchResult;

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
        if (data == null || target == null) {
            throw new IllegalArgumentException(
                    data == null ? "Data array cannot be null" : "Search target cannot be null");
        }
    }
}