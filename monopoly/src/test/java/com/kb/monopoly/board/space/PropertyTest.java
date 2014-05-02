/**
 * com.kb.monopoly.board.OwnableTest.java
 *
 * Created - 22 Apr 2014
 * Last Updated - 22 Apr 2014
 */
package com.kb.monopoly.board.space;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.kb.monopoly.board.space.Station;

/**
 * @author Kyle
 * 
 */
public class PropertyTest {

    private Station kingsCross;

    @Before
    public void setUp() throws Exception {
        kingsCross = new Station("Kings Cross Station");
    }

    @Test
    public void canGetValue() throws Exception {
        assertEquals(200, kingsCross.getValue());
    }

    @Test
    public void canGetMortgageValue() throws Exception {
        assertEquals(100, kingsCross.getMortgageValue());
    }

    @Test
    public void canMortgage() throws Exception {
        kingsCross.mortgage(true);
        assertTrue(kingsCross.isMortgaged());

        kingsCross.mortgage(false);
        assertFalse(kingsCross.isMortgaged());
    }
}
