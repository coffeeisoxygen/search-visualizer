package com.coffeecode.service.loader;

import com.coffeecode.model.DictionaryData;

public interface IDictionaryLoader {
    DictionaryData load(String filePath);

    DictionaryData loadBothDictionaries(String sourcePath, String targetPath);
}
