package com.coffeecode.load;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coffeecode.exception.DictionaryLoadException;
import com.coffeecode.model.DictionaryData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonLoad implements ILoadAble {
    private static final Logger logger = LoggerFactory.getLogger(JsonLoad.class);
    private final ObjectMapper mapper;
    private final Map<String, DictionaryData> cache;
    private final FileValidator fileValidator;
    private final DictionaryMapper dictionaryMapper;

    public JsonLoad() {
        this.mapper = new ObjectMapper();
        this.cache = new ConcurrentHashMap<>();
        this.fileValidator = new FileValidator();
        this.dictionaryMapper = new DictionaryMapper();
    }

    @Override
    public DictionaryData load(String filePath) {
        try {
            // Check cache first
            if (cache.containsKey(filePath)) {
                logger.debug("Retrieved dictionary from cache: {}", filePath);
                return cache.get(filePath);
            }

            fileValidator.validate(filePath);
            JsonNode rootNode = mapper.readTree(new File(filePath));
            DictionaryData dictionary = dictionaryMapper.mapToDictionary(rootNode);

            // Cache the result
            cache.put(filePath, dictionary);
            logger.info("Successfully loaded dictionary from: {}", filePath);

            return dictionary;
        } catch (IOException e) {
            logger.error("Failed to load dictionary file: {}", filePath, e);
            throw new DictionaryLoadException("Error loading dictionary file: " + filePath, e);
        }
    }

    @Override
    public DictionaryData loadBothDictionaries(String sourcePath, String targetPath) {
        try {
            DictionaryData sourceDict = load(sourcePath);
            DictionaryData targetDict = load(targetPath);
            return dictionaryMapper.mergeDictionaries(sourceDict, targetDict);
        } catch (Exception e) {
            logger.error("Failed to load dictionaries: {} and {}", sourcePath, targetPath, e);
            throw new DictionaryLoadException("Error loading dictionary files", e);
        }
    }
}
