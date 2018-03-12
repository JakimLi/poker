package org.poker;

import static java.util.Arrays.stream;
import static java.util.Comparator.reverseOrder;

public enum Rank {

    HIGH(1, 1, 1, 1, 1),
    ONE_PAIR(2, 1, 1, 1),
    TWO_PAIR(2, 2, 1),
    THREE_OF_KIND(3, 1, 1);

    private int[] shape;

    Rank(int... shape) {
        this.shape = shape;
    }

    static Rank rank(Hand hand) {
        return stream(Rank.values())
                .sorted(reverseOrder())
                .filter(rank -> hand.match(rank.shape))
                .findFirst()
                .orElse(HIGH);
    }
}
