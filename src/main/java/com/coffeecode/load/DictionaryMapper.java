package com.coffeecode.load;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coffeecode.exception.DictionaryLoadException;
import com.coffeecode.model.DictionaryData;
import com.fasterxml.jackson.databind.JsonNode;

public class DictionaryMapper {
    private static final Logger logger = LoggerFactory.getLogger(DictionaryMapper.class);

    public DictionaryData mapToDictionary(JsonNode rootNode) {
        if (rootNode == null) {
            throw new DictionaryLoadException("Root node is null");
        }

        JsonNode dataArray = rootNode.get("data");
        validateJsonStructure(dataArray);

        Map<String, String> indToEng = new HashMap<>();
        Map<String, String> engToInd = new HashMap<>();

        dataArray.forEach(entry -> {
            String indonesian = getRequiredField(entry, "indonesian").toLowerCase().trim();
            String english = getRequiredField(entry, "english").toLowerCase().trim();

            if (indToEng.containsKey(indonesian) || engToInd.containsKey(english)) {
                logger.warn("Duplicate entry found: {} - {}", indonesian, english);
                return;
            }

            indToEng.put(indonesian, english);
            engToInd.put(english, indonesian);
        });

        if (indToEng.isEmpty()) {
            throw new DictionaryLoadException("No valid entries found in dictionary");
        }

        return new DictionaryData(indToEng, engToInd);
    }

    public DictionaryData mergeDictionaries(DictionaryData source, DictionaryData target) {
        Map<String, String> mergedIndToEng = new HashMap<>(source.getIndToEng());
        Map<String, String> mergedEngToInd = new HashMap<>(target.getEngToInd());

        validateMergedDictionaries(mergedIndToEng, mergedEngToInd);
        return new DictionaryData(mergedIndToEng, mergedEngToInd);
    }

    private void validateJsonStructure(JsonNode dataArray) {
        if (dataArray == null || !dataArray.isArray()) {
            logger.error("Invalid JSON structure: missing or invalid data array");
            throw new DictionaryLoadException("Invalid JSON format: 'data' array not found");
        }
    }

    private String getRequiredField(JsonNode node, String fieldName) {
        JsonNode field = node.get(fieldName);
        if (field == null || field.asText().isEmpty()) {
            logger.error("Missing required field: {}", fieldName);
            throw new DictionaryLoadException("Missing required field: " + fieldName);
        }
        return field.asText();
    }

    private void validateMergedDictionaries(Map<String, String> indToEng, Map<String, String> engToInd) {
        if (indToEng.isEmpty() || engToInd.isEmpty()) {
            logger.error("Invalid merged dictionaries: empty maps detected");
            throw new DictionaryLoadException("Invalid merged dictionaries");
        }
    }
}