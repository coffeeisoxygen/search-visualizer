package com.coffeecode;

import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coffeecode.exception.DictionaryLoadException;
import com.coffeecode.model.DictionaryData;
import com.coffeecode.service.loader.IDictionaryLoader;
import com.coffeecode.service.loader.JsonDictionaryLoader;
import com.coffeecode.view.gui.MainFrame;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Starting Dictionary Application");
        IDictionaryLoader loader = new JsonDictionaryLoader();

        try {
            logger.debug("Loading dictionary files...");
            DictionaryData dictionary = loader.loadBothDictionaries(
                    "src/main/resources/data/indonesian.json",
                    "src/main/resources/data/english.json");

            logger.info("Dictionary loaded with {} entries", dictionary.size());

            SwingUtilities.invokeLater(() -> {
                try {
                    MainFrame frame = new MainFrame(dictionary);
                    frame.setVisible(true);
                    logger.info("GUI initialized successfully");
                } catch (Exception e) {
                    logger.error("Failed to initialize GUI", e);
                }
            });

        } catch (DictionaryLoadException e) {
            logger.error("Failed to load dictionaries", e);
        }
    }
}
