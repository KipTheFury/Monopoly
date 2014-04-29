/**
 * com.kb.monopoly.board.RailStationTest.java
 *
 * Created - 25 Apr 2014
 * Last Updated - 25 Apr 2014
 */
package com.kb.monopoly.board;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.kb.monopoly.player.Player;

/**
 * @author Kyle
 * 
 */
public class StationTest {

    private Station kingsCross, fenchurch, liverpoolSt, marylebone;

    @Before
    public void setUp() throws Exception {
        kingsCross = new Station("Kings Cross");
        fenchurch = new Station("Fenchurch St");
        liverpoolSt = new Station("Liverpool Street");
        marylebone = new Station("Marylebone");
    }

    @Test(expected = IllegalStateException.class)
    public void canCalculateRent() throws Exception {

        Player mockPlayer = mock(Player.class);
        ArrayList<Property> mockInventory = new ArrayList<Property>();
        mockInventory.add(kingsCross);

        kingsCross.setOwner(mockPlayer);

        when(mockPlayer.getInventory()).thenReturn(mockInventory);

        assertEquals(25, kingsCross.calculateRent());

        mockInventory.add(fenchurch);

        assertEquals(50, kingsCross.calculateRent());

        mockInventory.add(liverpoolSt);

        assertEquals(100, kingsCross.calculateRent());

        mockInventory.add(marylebone);

        assertEquals(200, kingsCross.calculateRent());

        mockInventory.add(kingsCross);

        kingsCross.calculateRent();
    }
}
