package com.coffeecode.service.search;

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

    protected void validateInput(String[] data, String target) {
        if (data == null) {
            throw new IllegalArgumentException("Data array cannot be null");
        }
        if (target == null) {
            throw new IllegalArgumentException("Search target cannot be null");
        }
    }
}