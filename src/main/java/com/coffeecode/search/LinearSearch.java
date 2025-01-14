package com.coffeecode.search;

import java.util.ArrayList;
import java.util.List;

public class LinearSearch extends AbstractSearch {
    @Override
    public SearchResult search(String[] data, String target) {
        List<Integer> steps = new ArrayList<>();
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < data.length; i++) {
            steps.add(i);
            if (data[i].equals(target)) {
                return createResult(steps, i, startTime);
            }
        }

        return createResult(steps, -1, startTime);
    }

    @Override
    public String getName() {
        return "Linear Search";
    }
}
