package com.coffeecode.search;

import java.util.ArrayList;
import java.util.List;

public class SearchResult {
    private final List<Integer> steps;
    private final int foundIndex;
    private final long duration;
    
    public SearchResult(List<Integer> steps, int foundIndex, long duration) {
        this.steps = new ArrayList<>(steps);
        this.foundIndex = foundIndex;
        this.duration = duration;
    }
    
    public List<Integer> getSteps() {
        return steps;
    }
    
    public int getFoundIndex() {
        return foundIndex;
    }
    
    public long getDuration() {
        return duration;
    }
    
    public boolean isFound() {
        return foundIndex != -1;
    }
}