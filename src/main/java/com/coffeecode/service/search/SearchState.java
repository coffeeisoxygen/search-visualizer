package com.coffeecode.service.search;

public interface SearchState {
    int getCurrentIndex();

    int getLowIndex(); // -1 for linear search

    int getMidIndex(); // -1 for linear search

    int getHighIndex(); // -1 for linear search

    boolean isBinarySearch();
}