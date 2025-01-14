package com.coffeecode.search;

public class BinarySearchState implements SearchState {
    private final int low;
    private final int mid;
    private final int high;

    public BinarySearchState(int low, int mid, int high) {
        this.low = low;
        this.mid = mid;
        this.high = high;
    }

    @Override
    public int getCurrentIndex() {
        return mid;
    }

    @Override
    public int getLowIndex() {
        return low;
    }

    @Override
    public int getMidIndex() {
        return mid;
    }

    @Override
    public int getHighIndex() {
        return high;
    }

    @Override
    public boolean isBinarySearch() {
        return true;
    }
}
