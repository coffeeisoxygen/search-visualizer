package com.coffeecode.controller;

import com.coffeecode.model.SearchResult;
import com.coffeecode.service.search.BinarySearch;
import com.coffeecode.service.search.ISearchable;
import com.coffeecode.service.search.LinearSearch;
import com.coffeecode.service.search.SearchObserver;

public class SearchController {
    private ISearchable searchStrategy;
    private SearchObserver observer;

    public SearchController() {
        this.searchStrategy = new LinearSearch();
    }

    public void setSearchStrategy(boolean isBinarySearch) {
        this.searchStrategy = isBinarySearch ? new BinarySearch() : new LinearSearch();
        if (observer != null) {
            this.searchStrategy.setObserver(observer);
        }
    }

    public void setObserver(SearchObserver observer) {
        this.observer = observer;
        if (searchStrategy != null) {
            searchStrategy.setObserver(observer);
        }
    }

    public SearchResult search(String[] data, String target) {
        if (searchStrategy == null) {
            throw new IllegalStateException("Search strategy not set");
        }
        return searchStrategy.search(data, target);
    }
}
