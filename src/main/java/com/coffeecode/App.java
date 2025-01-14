package com.coffeecode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coffeecode.exception.DictionaryLoadException;
import com.coffeecode.load.ILoadAble;
import com.coffeecode.load.JsonLoad;
import com.coffeecode.model.DictionaryData;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Starting Dictionary Application");
        ILoadAble loader = new JsonLoad();

        try {
            logger.debug("Loading dictionary files...");
            DictionaryData dictionary = loader.loadBothDictionaries(
                    "src/main/resources/data/indonesian.json",
                    "src/main/resources/data/english.json");

            // Display dictionary size
            logger.info("Dictionary loaded with {} entries", dictionary.size());

            // Display Indonesian to English mappings
            logger.info("Indonesian to English mappings:");
            dictionary.getIndToEng().forEach((indo, eng) -> logger.info("{} -> {}", indo, eng));

            // Display English to Indonesian mappings
            logger.info("English to Indonesian mappings:");
            dictionary.getEngToInd().forEach((eng, indo) -> logger.info("{} -> {}", eng, indo));

        } catch (DictionaryLoadException e) {
            logger.error("Failed to load dictionaries", e);
        }
        logger.info("Application finished");
    }
}
