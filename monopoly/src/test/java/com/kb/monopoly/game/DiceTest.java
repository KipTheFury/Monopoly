/**
 * com.kb.monopoly.game.DiceTest.java
 *
 * Created - 22 Apr 2014
 * Last Updated - 22 Apr 2014
 */
package com.kb.monopoly.game;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Kyle
 * 
 */
public class DiceTest {

    private DiceRoller d;

    @Before
    public void setUp() throws Exception {
        d = new DiceRoller();
    }

    @Test
    public void canRoll() throws Exception {
        int roll = d.roll();

        assertTrue(roll > 0 && roll <= 6);
    }
}
