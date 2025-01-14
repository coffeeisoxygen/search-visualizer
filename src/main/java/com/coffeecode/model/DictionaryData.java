package com.coffeecode.model;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class DictionaryData {
    private final Map<String, String> indToEng;
    private final Map<String, String> engToInd;

    public DictionaryData(Map<String, String> indToEng, Map<String, String> engToInd) {
        this.indToEng = Collections.unmodifiableMap(new TreeMap<>(indToEng));
        this.engToInd = Collections.unmodifiableMap(new TreeMap<>(engToInd));
    }

    public Map<String, String> getIndToEng() {
        return indToEng;
    }

    public Map<String, String> getEngToInd() {
        return engToInd;
    }

    public int size() {
        return indToEng.size();
    }

    public boolean isEmpty() {
        return indToEng.isEmpty() || engToInd.isEmpty();
    }

    public String[] getIndoWords() {
        return indToEng.keySet().toArray(String[]::new);
    }

    public String[] getEngWords() {
        return engToInd.keySet().toArray(String[]::new);
    }
}
