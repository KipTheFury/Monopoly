/**
 * com.kb.monopoly.board.UtilityTest.java
 *
 * Created - 25 Apr 2014
 * Last Updated - 25 Apr 2014
 */
package com.kb.monopoly.board.space;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.kb.monopoly.board.space.Property;
import com.kb.monopoly.board.space.Station;
import com.kb.monopoly.board.space.Utility;
import com.kb.monopoly.player.Player;

/**
 * @author Kyle
 * 
 */
public class UtilityTest {

    private Utility electric, water;

    @Before
    public void setUp() throws Exception {
        electric = new Utility("Electric");
        water = new Utility("Water");
    }

    @Test(expected = IllegalStateException.class)
    public void canCalculateRent() throws Exception {

        Player mockPlayer = mock(Player.class);
        ArrayList<Property> mockInventory = new ArrayList<Property>();
        mockInventory.add(electric);
        mockInventory.add(new Station("Station"));

        electric.setOwner(mockPlayer);

        when(mockPlayer.getInventory()).thenReturn(mockInventory);

        int diceRoll = 4;

        assertEquals(diceRoll * 4, electric.calculateRent(diceRoll));

        mockInventory.add(water);

        assertEquals(diceRoll * 10, electric.calculateRent(diceRoll));

        mockInventory.add(water);

        electric.calculateRent(diceRoll);
    }

    @Test(expected = IllegalAccessException.class)
    public void cannotCalculateRentWithNoOwner() throws Exception {
        electric.calculateRent(3);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void cannotCalculateRentWithoutDiceRoll() throws Exception {
        electric.calculateRent();
    }

}
