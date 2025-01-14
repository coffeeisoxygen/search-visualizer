package com.coffeecode.exception;

public class DictionaryLoadException extends RuntimeException {
    public DictionaryLoadException(String message) {
        super(message);
    }

    public DictionaryLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}