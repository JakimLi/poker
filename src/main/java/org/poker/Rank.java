package org.poker;

import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.Comparator.reverseOrder;

public enum Rank {

    HIGH(hand -> true),
    ONE_PAIR(Rank::onePair);

    private Qualifier qualifier;

    Rank(Qualifier qualifier) {
        this.qualifier = qualifier;
    }

    private static boolean onePair(Hand hand) {
        Map<Integer, Integer> counts = new HashMap<>();
        hand.cards.forEach(card -> {
            Integer integer = counts.get(card.value);
            counts.put(card.value, (integer == null ? 0 : integer) + 1);
        });
        return counts.values().stream().anyMatch(v -> v == 2);
    }

    static Rank rank(Hand hand) {
        return stream(Rank.values())
                .sorted(reverseOrder())
                .filter(rank -> rank.qualifier.qualified(hand))
                .findFirst()
                .orElse(HIGH);
    }

    interface Qualifier {
        boolean qualified(Hand hand);
    }
}
