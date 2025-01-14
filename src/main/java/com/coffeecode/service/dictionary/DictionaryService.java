package com.coffeecode.service.dictionary;

import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coffeecode.model.DictionaryData;
import com.coffeecode.service.loader.IDictionaryLoader;

public class DictionaryService implements IDictionaryService {
    private static final Logger logger = LoggerFactory.getLogger(DictionaryService.class);
    private final IDictionaryLoader loader;
    private DictionaryData dictionary;
    private final ConcurrentHashMap<String, String> translationCache;

    public DictionaryService(IDictionaryLoader loader) {
        this.loader = loader;
        this.translationCache = new ConcurrentHashMap<>();
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
        String cacheKey = getCacheKey(word, isIndToEng);
        return translationCache.computeIfAbsent(cacheKey, k -> {
            String translation = isIndToEng ? dictionary.getIndToEng().get(word) : dictionary.getEngToInd().get(word);
            logger.debug("Cache miss for: {}", word);
            return translation;
        });
    }

    private String getCacheKey(String word, boolean isIndToEng) {
        return isIndToEng ? "indo:" + word : "eng:" + word;
    }

    private void validateDictionaryLoaded() {
        if (dictionary == null) {
            throw new IllegalStateException("Dictionary not loaded");
        }
    }
}