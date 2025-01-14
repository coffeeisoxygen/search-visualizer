package com.coffeecode.service.search;

import java.util.ArrayList;
import java.util.List;

import com.coffeecode.model.SearchResult;

public class BinarySearch extends AbstractSearch {
    @Override
    public SearchResult search(String[] data, String target) {
        validateInput(data, target);

        List<Integer> steps = new ArrayList<>();
        long startTime = System.nanoTime();

        int left = 0;
        int right = data.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            steps.add(mid);

            notifyStep(new BinarySearchState(left, mid, right));

            int comparison = target.compareTo(data[mid]);
            if (comparison == 0) {
                SearchResult result = new SearchResult(steps, mid, System.nanoTime() - startTime);
                notifyComplete(result);
                return result;
            }

            if (comparison < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        SearchResult result = new SearchResult(steps, -1, System.nanoTime() - startTime);
        notifyComplete(result);
        return result;
    }

    @Override
    public String getName() {
        return "Binary Search";
    }
}
