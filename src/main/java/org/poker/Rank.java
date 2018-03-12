package org.poker;

import static java.util.Arrays.stream;
import static java.util.Comparator.reverseOrder;

public enum Rank {

    HIGH(1, 1, 1, 1, 1),
    ONE_PAIR(2, 1, 1, 1);


    private int[] qualifier;

    Rank(int... qualifier) {
        this.qualifier = qualifier;
    }

    static Rank rank(Hand hand) {
        return stream(Rank.values())
                .sorted(reverseOrder())
                .filter(rank -> hand.match(rank.qualifier))
                .findFirst()
                .orElse(HIGH);
    }
}
