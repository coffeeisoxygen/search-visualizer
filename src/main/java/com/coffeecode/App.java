package com.coffeecode;

import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coffeecode.exception.DictionaryLoadException;
import com.coffeecode.gui.MainFrame;
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
