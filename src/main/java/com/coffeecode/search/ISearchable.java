package com.coffeecode.search;

public interface ISearchable {
    SearchResult search(String[] data, String target);

    void setObserver(SearchObserver observer);

    String getName();
}