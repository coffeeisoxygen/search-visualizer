package com.coffeecode.load;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileValidator {
    private static final Logger logger = LoggerFactory.getLogger(FileValidator.class);

    public void validate(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            logger.error("Invalid file path: null or empty");
            throw new IllegalArgumentException("File path cannot be null or empty");
        }

        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            logger.error("File does not exist: {}", filePath);
            throw new IllegalArgumentException("File does not exist: " + filePath);
        }

        if (!file.canRead()) {
            logger.error("File is not readable: {}", filePath);
            throw new IllegalArgumentException("File is not readable: " + filePath);
        }
    }
}