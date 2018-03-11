package org.poker;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

class Poker {

    private final Hand first;
    private final Hand second;

    Poker(String cards) {
        first = new Hand(first5(cards(cards)));
        second = new Hand(last5(cards(cards)));
    }

    private List<Card> last5(List<Card> cards) {
        return cards.stream().skip(5).collect(Collectors.toList());
    }

    private List<Card> first5(List<Card> cards) {
        return cards.stream().limit(5).collect(Collectors.toList());
    }

    private List<Card> cards(String cards) {
        return stream(cards.split(" ")).map(Card::new).collect(Collectors.toList());
    }

    int winner() {
        return first.compareTo(second) > 0 ? 1 : 2;
    }
}
