package com.coffeecode.service.dictionary;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.coffeecode.model.DictionaryData;

public class DictionaryValidator {
    public void validate(DictionaryData dictionary) {
        validateMappings(dictionary.getIndToEng(), dictionary.getEngToInd());
        validateSorting(dictionary.getIndToEng().keySet());
        validateSorting(dictionary.getEngToInd().keySet());
    }

    private void validateMappings(Map<String, String> indoToEng, Map<String, String> engToIndo) {
        Set<String> inconsistencies = indoToEng.entrySet().stream()
                .filter(e -> !e.getValue().equals(engToIndo.get(e.getKey())))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        if (!inconsistencies.isEmpty()) {
            throw new IllegalStateException("Inconsistent mappings found: " + inconsistencies);
        }
    }

    private void validateSorting(Set<String> words) {
        if (!isSorted(words)) {
            throw new IllegalStateException("Dictionary entries must be sorted");
        }
    }

    private boolean isSorted(Set<String> words) {
        String[] array = words.toArray(String[]::new);
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1].compareTo(array[i]) > 0)
                return false;
        }
        return true;
    }
}
