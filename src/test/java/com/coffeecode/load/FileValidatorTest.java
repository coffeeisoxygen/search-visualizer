package com.coffeecode.load;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FileValidatorTest {
    @TempDir
    File tempDir;
    private FileValidator validator;
    private File testFile;

    @BeforeEach
    void setUp() throws IOException {
        validator = new FileValidator();
        testFile = new File(tempDir, "test.json");
        testFile.createNewFile();
    }

    @Test
    void testValidFile() {
        assertDoesNotThrow(() -> validator.validate(testFile.getPath()));
    }

    @Test
    void testNullPath() {
        assertThrows(IllegalArgumentException.class, () -> validator.validate(null));
    }

    @Test
    void testEmptyPath() {
        assertThrows(IllegalArgumentException.class, () -> validator.validate(""));
    }

    @Test
    void testNonExistentFile() {
        assertThrows(IllegalArgumentException.class, 
            () -> validator.validate("nonexistent.json"));
    }
}
