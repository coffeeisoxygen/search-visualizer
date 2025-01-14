package com.coffeecode.controller;

import com.coffeecode.model.SearchResult;
import com.coffeecode.service.dictionary.IDictionaryService;
import com.coffeecode.service.search.BinarySearch;
import com.coffeecode.service.search.ISearchable;
import com.coffeecode.service.search.LinearSearch;

public class DictionaryController {
    private final IDictionaryService dictionaryService;
    private final SearchController searchController;
    private ISearchable searchStrategy;

    public DictionaryController(IDictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
        this.searchController = new SearchController();
        this.searchStrategy = new LinearSearch(); // default
    }

    public void setSearchStrategy(boolean isBinarySearch) {
        this.searchStrategy = isBinarySearch ? new BinarySearch() : new LinearSearch();
    }

    public SearchResult search(String word, boolean isIndToEng) {
        String[] data = isIndToEng ? dictionaryService.getIndoWords() : dictionaryService.getEngWords();
        return searchStrategy.search(data, word);
    }

    public String translate(String word, boolean isIndToEng) {
        return dictionaryService.translate(word, isIndToEng);
    }

    public String[] getWords(boolean isIndoWords) {
        return isIndoWords ? dictionaryService.getIndoWords() : dictionaryService.getEngWords();
    }
}
