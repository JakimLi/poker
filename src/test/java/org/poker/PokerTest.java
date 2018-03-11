package org.poker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PokerTest {

    @Test
    public void high_card_win() throws Exception {
        assertThat(new Poker("2H 3D 5S 9C KD 2C 3H 4S 8C AH").winner(), is(2));
    }

    @Test
    public void high_card_same_second_high_card_wins() throws Exception {
        assertThat(new Poker("2H 3D 5S 9C AD 2C 3H 4S 8C AH").winner(), is(1));
    }

    @Test
    public void one_pair_win_on_high_cards() throws Exception {
        assertThat(new Poker("2H 2D 5S 9C KD 2C 3H 4S 8C AH").winner(), is(1));
    }
    
    @Test
    public void both_one_pair_high_pair_wins() throws Exception {
        assertThat(new Poker("2H 2D 5S 9C KD 3C 3H 4S 8C AH").winner(), is(2));
    }
}
