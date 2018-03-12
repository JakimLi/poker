package org.poker;

import org.poker.Rank.Qualifier;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;

class Qualifiers {
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

    static Qualifier compose(Qualifier... qualifiers) {
        return hand -> stream(qualifiers).allMatch(qualifier -> qualifier.qualify(hand));
    }
}
