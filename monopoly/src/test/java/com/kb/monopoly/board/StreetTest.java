/**
 * 
 */
package com.kb.monopoly.board;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.kb.monopoly.board.PropertySets.SetColour;

/**
 * @author kbennett
 * 
 */
public class StreetTest {

    private Street mayfair;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        mayfair = new Street("Mayfair", 350, 100, SetColour.DarkBlue, 50, new int[] { 50, 200, 600, 1400, 1700, 2000 });
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCreateStreetWithInvalidRentLevels() throws Exception {
        mayfair = new Street("Mayfair", 350, 100, SetColour.DarkBlue, 50, new int[] { 50, 200, 600, 1400 });
    }

    @Test
    public void canAddHouses() throws Exception {
        mayfair.addHouse();
        assertEquals(1, mayfair.getBuildingCount());
    }

    @Test(expected = IllegalStateException.class)
    public void cannotAddMoreThan4Houses() throws Exception {
        for (int i = 0; i < 4; i++) {
            mayfair.addHouse();
        }
        assertEquals(4, mayfair.getBuildingCount());

        mayfair.addHouse();
    }

    @Test
    public void canAddHotel() throws Exception {

        for (int i = 0; i < 4; i++) {
            mayfair.addHouse();
        }
        mayfair.addHotel();

        assertEquals(5, mayfair.getBuildingCount());
    }

    @Test
    public void canCalculateRent() throws Exception {

        assertEquals(50, mayfair.calculateRent());
        mayfair.addHouse();
        assertEquals(200, mayfair.calculateRent());
        mayfair.addHouse();
        assertEquals(600, mayfair.calculateRent());
        mayfair.addHouse();
        assertEquals(1400, mayfair.calculateRent());
        mayfair.addHouse();
        assertEquals(1700, mayfair.calculateRent());
        mayfair.addHotel();
        assertEquals(2000, mayfair.calculateRent());
    }
}
