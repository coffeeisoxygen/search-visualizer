package com.coffeecode.view.gui;

public class SearchRequest {
    public final String searchTerm;
    public final boolean isIndToEng;

    public SearchRequest(String searchTerm, boolean isIndToEng) {
        this.searchTerm = searchTerm;
        this.isIndToEng = isIndToEng;
    }
}
