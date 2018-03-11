package org.poker;

import java.util.Arrays;
import java.util.Optional;

import static org.poker.Card.Colored.colored;

class Card implements Comparable<Card> {

    final int value;
    private final Suit suit;

    Card(String card) {
        value = value(card.substring(0, 1));
        suit = suit(card.substring(1, 2));
    }

    private Suit suit(String suit) {
        return Suit.valueOf(suit);
    }

    private int value(String value) {
        return colored(value)
                .map(colored -> colored.value)
                .orElseGet(() -> Integer.valueOf(value));
    }

    @Override
    public int compareTo(Card o) {
        return this.value - o.value;
    }

    enum Suit {
        S, H, C, D
    }

    enum Colored {
        JACK(11, "J"), QUEEN(12, "Q"), KING(13, "K"), ACE(14, "A");

        private int value;
        private String symbol;

        Colored(int value, String symbol) {
            this.value = value;
            this.symbol = symbol;
        }

        static Optional<Colored> colored(String value) {
            return Arrays.stream(Colored.values())
                    .filter(v -> value.equals(v.symbol))
                    .findFirst();
        }
    }
}
