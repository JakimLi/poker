package org.poker;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.joining;

public enum Rank {

    HIGH(shape(1, 1, 1, 1, 1)),
    ONE_PAIR(shape(2, 1, 1, 1)),
    TWO_PAIR(shape(2, 2, 1)),
    THREE_OF_KIND(shape(3, 1, 1)),
    STRAIGHT(both(shape(1, 1, 1, 1, 1), consecutive())),
    FLUSH(sameSuit()),
    FULL_HOUSE(shape(3, 2));

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

    static Qualifier shape(int... shape) {
        return hand -> {
            String cards = hand.counts.values().stream().map(String::valueOf).collect(joining());
            String expected = stream(shape).mapToObj(String::valueOf).collect(Collectors.joining());
            return cards.equals(expected);
        };
    }

    static Qualifier consecutive() {
        return hand -> {
            List<Card> cards = hand.cards;
            return cards.get(0).value - cards.get(cards.size() - 1).value == cards.size() - 1;
        };
    }

    static Qualifier sameSuit() {
        return hand -> {
            Set<Card.Suit> suits = new HashSet<>();
            hand.cards.forEach(card -> suits.add(card.suit));
            return suits.size() == 1;
        };
    }

    static Qualifier both(Qualifier... qualifiers) {
        return hand -> stream(qualifiers).allMatch(qualifier -> qualifier.qualify(hand));
    }

    interface Qualifier {
        boolean qualify(Hand hand);
    }
}
