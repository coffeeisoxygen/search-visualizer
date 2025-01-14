package com.coffeecode.service.dictionary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coffeecode.model.DictionaryData;
import com.coffeecode.service.loader.IDictionaryLoader;

public class DictionaryService implements IDictionaryService {
    private static final Logger logger = LoggerFactory.getLogger(DictionaryService.class);
    private final IDictionaryLoader loader;
    private DictionaryData dictionary;

    public DictionaryService(IDictionaryLoader loader) {
        this.loader = loader;
    }

    @Override
    public void loadDictionary(String indoPath, String engPath) {
        dictionary = loader.loadBothDictionaries(indoPath, engPath);
    }

    @Override
    public String[] getIndoWords() {
        validateDictionaryLoaded();
        return dictionary.getIndoWords();
    }

    @Override
    public String[] getEngWords() {
        validateDictionaryLoaded();
        return dictionary.getEngWords();
    }

    @Override
    public String translate(String word, boolean isIndToEng) {
        validateDictionaryLoaded();
        if (isIndToEng) {
            return dictionary.getIndToEng().get(word);
        }
        return dictionary.getEngToInd().get(word);
    }

    private void validateDictionaryLoaded() {
        if (dictionary == null) {
            throw new IllegalStateException("Dictionary not loaded");
        }
    }
}