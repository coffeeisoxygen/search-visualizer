package com.coffeecode;

import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coffeecode.controller.DictionaryController;
import com.coffeecode.service.dictionary.DictionaryService;
import com.coffeecode.service.dictionary.IDictionaryService;
import com.coffeecode.service.loader.IDictionaryLoader;
import com.coffeecode.service.loader.JsonDictionaryLoader;
import com.coffeecode.view.gui.MainFrame;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Starting Dictionary Application");
        IDictionaryLoader loader = new JsonDictionaryLoader();
        IDictionaryService dictionaryService = new DictionaryService(loader);
        DictionaryController dictionaryController = new DictionaryController(dictionaryService);

        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame(dictionaryController);
            frame.setVisible(true);
            logger.info("GUI initialized successfully");
        });
    }
}
