package org.poker;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Comparator.reverseOrder;

class Hand implements Comparable<Hand> {

    private List<Card> cards;

    Hand(List<Card> cards) {
        this.cards = cards.stream()
                .sorted(reverseOrder())
                .collect(Collectors.toList());
    }

    @Override
    public int compareTo(Hand hand) {
        return IntStream.range(0, 5)
                .mapToObj(index -> cards.get(index).compareTo(hand.cards.get(index)))
                .filter(integer -> integer != 0)
                .findFirst()
                .orElse(0);
    }
}
