package org.poker;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Comparator.reverseOrder;
import static org.poker.Rank.rank;

class Hand implements Comparable<Hand> {

    List<Card> cards;

    Hand(List<Card> cards) {
        this.cards = cards.stream()
                .sorted(reverseOrder())
                .collect(Collectors.toList());
    }

    @Override
    public int compareTo(Hand hand) {
        int ranking = rank(this).compareTo(rank(hand));
        if (same(ranking)) {
            return compareHighCard(hand);
        }
        return ranking;
    }

    private boolean same(int rank) {
        return rank == 0;
    }

    private Integer compareHighCard(Hand hand) {
        return IntStream.range(0, 5)
                .mapToObj(index -> cards.get(index).compareTo(hand.cards.get(index)))
                .filter(integer -> integer != 0)
                .findFirst()
                .orElse(0);
    }
}
