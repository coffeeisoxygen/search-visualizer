package com.coffeecode.search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch extends AbstractSearch {
    @Override
    public SearchResult search(String[] data, String target) {
        List<Integer> steps = new ArrayList<>();
        long startTime = System.currentTimeMillis();

        int left = 0;
        int right = data.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            steps.add(mid);

            int comparison = target.compareTo(data[mid]);
            if (comparison == 0) {
                return createResult(steps, mid, startTime);
            }

            if (comparison < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return createResult(steps, -1, startTime);
    }

    @Override
    public String getName() {
        return "Binary Search";
    }
}
