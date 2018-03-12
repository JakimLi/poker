package org.poker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.joining;
import static org.poker.Rank.rank;

class Hand implements Comparable<Hand> {

    final List<Card> cards;

    final Map<Integer, Integer> counts = new HashMap<>();

    Hand(List<Card> cards) {
        count(cards);
        this.cards = cards.stream()
                .sorted(reverseOrder())
                .sorted((card1, card2) -> counts.get(card2.value) - counts.get(card1.value))
                .collect(Collectors.toList());
    }

    @Override
    public int compareTo(Hand hand) {
        int ranking = rank(this).compareTo(rank(hand));
        return same(ranking) ? compareHighCard(hand) : ranking;
    }

    public String shape() {
        return this.counts.values().stream().map(String::valueOf).collect(joining());
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

    private void count(List<Card> cards) {
        cards.forEach(card -> {
            Integer integer = counts.get(card.value);
            counts.put(card.value, (integer == null ? 0 : integer) + 1);
        });
    }
}
