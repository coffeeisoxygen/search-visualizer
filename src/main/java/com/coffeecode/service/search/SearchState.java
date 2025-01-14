package com.coffeecode.service.search;

public interface SearchState {
    int getCurrentIndex();
    int getLowIndex();
    int getMidIndex();
    int getHighIndex();
    boolean isBinarySearch();
}