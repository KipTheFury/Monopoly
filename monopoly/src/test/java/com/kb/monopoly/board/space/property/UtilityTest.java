/**
 * com.kb.monopoly.board.UtilityTest.java
 *
 * Created - 25 Apr 2014
 * Last Updated - 25 Apr 2014
 */
package com.kb.monopoly.board.space.property;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.kb.monopoly.board.space.property.PropertyPortfolio;
import com.kb.monopoly.board.space.property.Station;
import com.kb.monopoly.board.space.property.Utility;
import com.kb.monopoly.player.Player;

/**
 * @author Kyle
 * 
 */
public class UtilityTest
{

    private Utility electric, water;

    @Before
    public void setUp() throws Exception
    {
        electric = new Utility("Electric");
        water = new Utility("Water");
    }

    @Test(expected = IllegalStateException.class)
    public void canCalculateRent() throws Exception
    {

        final Player mockPlayer = mock(Player.class);
        final PropertyPortfolio mockInventory = new PropertyPortfolio(mockPlayer);
        mockInventory.buy(electric);
        mockInventory.buy(new Station("Station"));

        electric.setOwner(mockPlayer);

        when(mockPlayer.getPortfolio()).thenReturn(mockInventory);

        final int diceRoll = 4;

        assertEquals(diceRoll * 4, electric.calculateRent(diceRoll));

        mockInventory.buy(water);

        assertEquals(diceRoll * 10, electric.calculateRent(diceRoll));

        mockInventory.buy(water);

        electric.calculateRent(diceRoll);
    }

    @Test(expected = IllegalAccessException.class)
    public void cannotCalculateRentWithNoOwner() throws Exception
    {
        electric.calculateRent(3);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void cannotCalculateRentWithoutDiceRoll() throws Exception
    {
        electric.calculateRent();
    }

}
