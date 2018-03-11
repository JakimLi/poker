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
}
