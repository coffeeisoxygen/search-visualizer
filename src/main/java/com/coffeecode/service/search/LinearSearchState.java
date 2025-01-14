package com.coffeecode.service.search;

public class LinearSearchState implements SearchState {
    private final int currentIndex;

    public LinearSearchState(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    @Override
    public int getCurrentIndex() {
        return currentIndex;
    }

    @Override
    public int getLowIndex() {
        return -1;
    }

    @Override
    public int getMidIndex() {
        return -1;
    }

    @Override
    public int getHighIndex() {
        return -1;
    }

    @Override
    public boolean isBinarySearch() {
        return false;
    }
}
