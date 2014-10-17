/**
 * com.kb.monopoly.board.RailStationTest.java
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
public class StationTest
{

    private Station kingsCross, fenchurch, liverpoolSt, marylebone;

    @Before
    public void setUp() throws Exception
    {
        kingsCross = new Station("Kings Cross");
        fenchurch = new Station("Fenchurch St");
        liverpoolSt = new Station("Liverpool Street");
        marylebone = new Station("Marylebone");
    }

    @Test(expected = IllegalStateException.class)
    public void canCalculateRent() throws Exception
    {

        final Player mockPlayer = mock(Player.class);
        final PropertyPortfolio mockInventory = new PropertyPortfolio(mockPlayer);
        mockInventory.buy(kingsCross);
        mockInventory.buy(new Utility("Utility"));

        kingsCross.setOwner(mockPlayer);

        when(mockPlayer.getPortfolio()).thenReturn(mockInventory);

        assertEquals(25, kingsCross.calculateRent());

        mockInventory.buy(fenchurch);

        assertEquals(50, kingsCross.calculateRent());

        mockInventory.buy(liverpoolSt);

        assertEquals(100, kingsCross.calculateRent());

        mockInventory.buy(marylebone);

        assertEquals(200, kingsCross.calculateRent());

        mockInventory.buy(kingsCross);

        kingsCross.calculateRent();
    }

    @Test(expected = IllegalAccessException.class)
    public void cannotCalculateRentWithNoOwner() throws Exception
    {
        kingsCross.calculateRent();
    }
}
