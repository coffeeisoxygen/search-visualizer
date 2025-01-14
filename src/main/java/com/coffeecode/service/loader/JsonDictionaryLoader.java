package com.coffeecode.service.loader;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coffeecode.exception.DictionaryLoadException;
import com.coffeecode.model.DictionaryData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDictionaryLoader implements IDictionaryLoader {
    private static final Logger logger = LoggerFactory.getLogger(JsonDictionaryLoader.class);
    private final ObjectMapper mapper;
    private final FileValidator fileValidator;
    private final DictionaryMapper dictionaryMapper;

    public JsonDictionaryLoader() {
        this.mapper = new ObjectMapper();
        this.fileValidator = new FileValidator();
        this.dictionaryMapper = new DictionaryMapper();
    }

    @Override
    public DictionaryData load(String filePath) {
        try {
            fileValidator.validate(filePath);
            JsonNode rootNode = mapper.readTree(new File(filePath));
            DictionaryData dictionary = dictionaryMapper.mapToDictionary(rootNode);

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
