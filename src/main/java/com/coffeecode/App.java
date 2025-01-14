package com.coffeecode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coffeecode.exception.DictionaryLoadException;
import com.coffeecode.load.ILoadAble;
import com.coffeecode.load.JsonLoad;
import com.coffeecode.model.DictionaryData;

/**
 * Hello world!
 *
 */
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

            logger.info("Testing Indonesian to English translation");
            String catTranslation = dictionary.getIndToEng().get("kucing");
            logger.debug("Translation 'kucing' -> '{}'", catTranslation);

            logger.info("Testing English to Indonesian translation");
            String dogTranslation = dictionary.getEngToInd().get("dog");
            logger.debug("Translation 'dog' -> '{}'", dogTranslation);

        } catch (DictionaryLoadException e) {
            logger.error("Failed to load dictionaries", e);
        }
        logger.info("Application finished");
    }
}
