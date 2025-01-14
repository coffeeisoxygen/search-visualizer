package com.coffeecode.service.dictionary;

public interface IDictionaryService {
    String[] getIndoWords();

    String[] getEngWords();

    String translate(String word, boolean isIndToEng);

    void loadDictionary(String indoPath, String engPath);
}
