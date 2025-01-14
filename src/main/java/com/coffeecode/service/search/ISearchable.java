package com.coffeecode.service.search;

import com.coffeecode.model.SearchResult;

public interface ISearchable {
    SearchResult search(String[] data, String target);

    void setObserver(SearchObserver observer);

    String getName();
}