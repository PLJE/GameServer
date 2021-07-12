package com.example.gameserver;

import java.util.Comparator;

class MiniComparator implements Comparator<Rank> {
    @Override
    public int compare(Rank first, Rank second) {
        double firstValue = Double.parseDouble(first.getScore());
        double secondValue = Double.parseDouble(second.getScore());

        // Order by descending
        if (firstValue > secondValue) {
            return -1;
        } else if (firstValue < secondValue) {
            return 1;
        } else {
            return 0;
        }
    }
}
