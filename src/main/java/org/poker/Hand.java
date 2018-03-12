package org.poker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.reverseOrder;
import static org.poker.Rank.rank;

class Hand implements Comparable<Hand> {

    private final List<Card> cards;

    private final Map<Integer, Integer> counts = new HashMap<>();

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

    boolean match(int[] pattern) {
        String cards = join(counts.values().stream());
        return cards.equals(join(pattern));
    }

    private String join(int[] pattern) {
        return Arrays.stream(pattern)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());
    }

    private String join(Stream<Integer> stream) {
        return stream
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private void count(List<Card> cards) {
        cards.forEach(card -> {
            Integer integer = counts.get(card.value);
            counts.put(card.value, (integer == null ? 0 : integer) + 1);
        });
    }
}
