package com.coffeecode.load;

import com.coffeecode.model.DictionaryData;

public interface ILoadAble {
    DictionaryData load(String filePath);
    DictionaryData loadBothDictionaries(String sourcePath, String targetPath);
}
