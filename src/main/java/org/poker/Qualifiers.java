package org.poker;

import org.poker.Rank.Qualifier;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.abs;
import static java.util.Arrays.stream;

class Qualifiers {
    static Qualifier shape(Integer... shape) {
        return hand -> {
            String expected = stream(shape).map(String::valueOf).collect(Collectors.joining());
            return hand.shape().equals(expected);
        };
    }

    static Qualifier consecutive() {
        return hand -> {
            List<Card> cards = hand.cards;
            return abs(cards.get(0).value - cards.get(cards.size() - 1).value) == cards.size() - 1;
        };
    }

    static Qualifier sameSuit() {
        return hand -> hand.cards.stream()
                .map(card -> card.suit)
                .collect(Collectors.toSet()).size() == 1;
    }

    static Qualifier compose(Qualifier... qualifiers) {
        return hand -> stream(qualifiers).allMatch(qualifier -> qualifier.qualify(hand));
    }
}
