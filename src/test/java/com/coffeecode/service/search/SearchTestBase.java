package com.coffeecode.service.search;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import com.coffeecode.model.SearchResult;

public abstract class SearchTestBase {
    protected String[] sortedData;
    protected TestSearchObserver observer;

    @BeforeEach
    void setUp() {
        sortedData = new String[] { "anjing", "burung", "kucing", "singa" };
        observer = new TestSearchObserver();
    }

    protected static class TestSearchObserver implements SearchObserver {
        private final List<SearchState> states = new ArrayList<>();

        @Override
        public void onSearchStep(SearchState state) {
            states.add(state);
        }

        public List<SearchState> getStates() {
            return states;
        }

        @Override
        public void onSearchComplete(SearchResult result) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
