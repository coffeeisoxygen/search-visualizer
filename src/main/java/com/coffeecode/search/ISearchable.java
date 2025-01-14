package com.coffeecode.search;

public interface ISearchable {
    SearchResult search(String[] data, String target);

    String getName();
}