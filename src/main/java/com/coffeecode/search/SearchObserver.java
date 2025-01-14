package com.coffeecode.search;

public interface SearchObserver {
    void onSearchStep(SearchState state);

    void onSearchComplete(SearchResult result);
}