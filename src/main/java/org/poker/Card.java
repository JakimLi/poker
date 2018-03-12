package org.poker;

import java.util.Optional;

import static java.util.Arrays.stream;
import static org.poker.Card.Colored.colored;

class Card implements Comparable<Card> {

    final int value;
    final Suit suit;

    Card(String card) {
        value = value(card.substring(0, 1));
        suit = suit(card.substring(1, 2));
    }

    @Override
    public int compareTo(Card o) {
        return this.value - o.value;
    }

    private Suit suit(String suit) {
        return Suit.valueOf(suit);
    }

    private int value(String value) {
        return colored(value)
                .map(colored -> colored.value)
                .orElseGet(() -> Integer.valueOf(value));
    }

    enum Suit {
        S, H, C, D
    }

    enum Colored {
        TEN(10, "T"), JACK(11, "J"), QUEEN(12, "Q"), KING(13, "K"), ACE(14, "A");

        private int value;
        private String symbol;

        Colored(int value, String symbol) {
            this.value = value;
            this.symbol = symbol;
        }

        static Optional<Colored> colored(String symbol) {
            return stream(Colored.values())
                    .filter(card -> symbol.equals(card.symbol))
                    .findFirst();
        }
    }
}
