package com.coffeecode.service.search;

import java.util.ArrayList;
import java.util.List;

import com.coffeecode.model.SearchResult;

public class LinearSearch extends AbstractSearch {
    @Override
    public SearchResult search(String[] data, String target) {
        validateInput(data, target);

        List<Integer> steps = new ArrayList<>();
        long startTime = System.nanoTime();

        for (int i = 0; i < data.length; i++) {
            steps.add(i);
            notifyStep(new LinearSearchState(i));

            if (data[i].equals(target)) {
                return new SearchResult(steps, i, System.nanoTime() - startTime);
            }
        }

        return new SearchResult(steps, -1, System.nanoTime() - startTime);
    }

    @Override
    public String getName() {
        return "Linear Search";
    }
}
