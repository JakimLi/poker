package org.poker;

import static java.util.Arrays.stream;
import static java.util.Comparator.reverseOrder;
import static org.poker.Qualifiers.compose;
import static org.poker.Qualifiers.consecutive;
import static org.poker.Qualifiers.sameSuit;
import static org.poker.Qualifiers.shape;

public enum Rank {

    HIGH(shape(1, 1, 1, 1, 1)),
    ONE_PAIR(shape(2, 1, 1, 1)),
    TWO_PAIR(shape(2, 2, 1)),
    THREE_OF_KIND(shape(3, 1, 1)),
    STRAIGHT(consecutive()),
    FLUSH(sameSuit()),
    FULL_HOUSE(shape(3, 2)),
    FOUR_OF_KIND(shape(4, 1)),
    STRAIGHT_FLUSH(compose(consecutive(), sameSuit()));

    private Qualifier qualifier;

    Rank(Qualifier qualifier) {
        this.qualifier = qualifier;
    }

    static Rank rank(Hand hand) {
        return stream(Rank.values())
                .sorted(reverseOrder())
                .filter(rank -> rank.qualifier.qualify(hand))
                .findFirst()
                .orElse(HIGH);
    }

    interface Qualifier {
        boolean qualify(Hand hand);
    }
}
