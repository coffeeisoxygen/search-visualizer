package com.coffeecode.controller;

import com.coffeecode.model.SearchResult;
import com.coffeecode.service.search.BinarySearch;
import com.coffeecode.service.search.ISearchable;
import com.coffeecode.service.search.LinearSearch;

public class SearchController {
    private ISearchable searchStrategy;

    public SearchController() {
        this.searchStrategy = new LinearSearch();
    }

    public void setSearchStrategy(boolean isBinarySearch) {
        this.searchStrategy = isBinarySearch ? new BinarySearch() : new LinearSearch();
    }

    public SearchResult search(String[] data, String target) {
        return searchStrategy.search(data, target);
    }
}
