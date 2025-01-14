package com.coffeecode.service.search;

import com.coffeecode.model.SearchResult;

public interface ISearchable {
    /**
     * Performs search operation on given data
     * @param data Sorted array for searching
     * @param target Element to find
     * @return SearchResult containing steps and result
     * @throws IllegalArgumentException if data or target is null
     */
    SearchResult search(String[] data, String target);
    
    /**
     * Sets observer for search visualization
     * @param observer SearchObserver implementation
     */
    void setObserver(SearchObserver observer);
    
    /**
     * @return Name of the search algorithm
     */
    String getName();
}