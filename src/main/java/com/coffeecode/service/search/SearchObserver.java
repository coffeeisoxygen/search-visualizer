package com.coffeecode.service.search;

import com.coffeecode.model.SearchResult;

public interface SearchObserver {
    void onSearchStep(SearchState state);

    void onSearchComplete(SearchResult result);
}