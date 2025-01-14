package com.coffeecode.search;

import java.util.ArrayList;
import java.util.List;

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
                SearchResult result = new SearchResult(steps, i, System.nanoTime() - startTime);
                notifyComplete(result);
                return result;
            }
        }

        SearchResult result = new SearchResult(steps, -1, System.nanoTime() - startTime);
        notifyComplete(result);
        return result;
    }

    @Override
    public String getName() {
        return "Linear Search";
    }
}
