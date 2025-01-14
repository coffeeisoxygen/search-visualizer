package com.coffeecode.controller;

import com.coffeecode.model.SearchResult;
import com.coffeecode.service.dictionary.IDictionaryService;

public class DictionaryController {
    private final IDictionaryService dictionaryService;
    private final SearchController searchController;

    public DictionaryController(IDictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
        this.searchController = new SearchController();
    }

    public String translate(String word, boolean isIndToEng) {
        return dictionaryService.translate(word, isIndToEng);
    }

    public String[] getWords(boolean isIndoWords) {
        return isIndoWords ? 
            dictionaryService.getIndoWords() : 
            dictionaryService.getEngWords();
    }

    public SearchResult search(String word, boolean isIndToEng) {
        return searchController.search(
            getWords(isIndToEng), 
            word
        );
    }

    public void setSearchAlgorithm(boolean isBinarySearch) {
        searchController.setSearchStrategy(isBinarySearch);
    }
}
