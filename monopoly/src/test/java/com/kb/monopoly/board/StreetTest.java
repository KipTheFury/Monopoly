/**
 * 
 */
package com.kb.monopoly.board;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.kb.monopoly.board.PropertySets.SetColour;
import com.kb.monopoly.player.Player;

/**
 * @author kbennett
 * 
 */
public class StreetTest {

    private static final String NAME = "Mayfair";
    private static final int VALUE = 350;
    private static final int BUILDING_COST = 50;
    private static final SetColour COLOUR = SetColour.DarkBlue;
    private static final int[] VALID_RENT = new int[] { 50, 200, 600, 1400, 1700, 2000 };
    private static final int[] INVALID_RENT = new int[] { 50, 200, 1400, 2000 };

    private Street mayfair;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        mayfair = new Street(NAME, VALUE, COLOUR, BUILDING_COST, VALID_RENT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCreateStreetWithInvalidRentLevels() throws Exception {
        new Street(NAME, VALUE, COLOUR, BUILDING_COST, INVALID_RENT);
    }

    @Test
    public void canAddHouses() throws Exception {
        mayfair.addHouse();
        assertEquals(1, mayfair.getBuildingCount());
    }

    @Test(expected = IllegalStateException.class)
    public void cannotAddMoreThan4Houses() throws Exception {
        addHouses(4, mayfair);
        assertEquals(4, mayfair.getBuildingCount());

        mayfair.addHouse();
    }

    @Test
    public void canAddHotel() throws Exception {

        addHouses(4, mayfair);
        mayfair.addHotel();

        assertEquals(5, mayfair.getBuildingCount());
    }

    @Test(expected = IllegalStateException.class)
    public void cannotAddHotelWithout4Houses() throws Exception {

        addHouses(2, mayfair);
        mayfair.addHotel();
    }

    @Test(expected = IllegalStateException.class)
    public void cannotAdd2Hotels() throws Exception {
        addHouses(4, mayfair);
        mayfair.addHotel();

        assertEquals(5, mayfair.getBuildingCount());

        mayfair.addHotel();
    }

    private void addHouses(int houses, Street street) {
        for (int i = 0; i < houses; i++) {
            street.addHouse();
        }
    }

    @Test
    public void canCalculateRent() throws Exception {

        ArrayList<Street> darkBlue = new ArrayList<Street>();
        Street parkLane = new Street(NAME, VALUE, COLOUR, BUILDING_COST, VALID_RENT);

        darkBlue.add(parkLane);
        darkBlue.add(mayfair);

        Player bob = new Player("Bob");

        bob.buy(mayfair);

        PropertySets.addPropertySet(COLOUR, darkBlue);

        mayfair.setOwner(bob);

        assertEquals(VALID_RENT[0], mayfair.calculateRent());

        bob.buy(parkLane);
        parkLane.setOwner(bob);

        assertEquals(VALID_RENT[0] * 2, mayfair.calculateRent());

        mayfair.addHouse();
        assertEquals(VALID_RENT[1], mayfair.calculateRent());
        mayfair.addHouse();
        assertEquals(VALID_RENT[2], mayfair.calculateRent());
        mayfair.addHouse();
        assertEquals(VALID_RENT[3], mayfair.calculateRent());
        mayfair.addHouse();
        assertEquals(VALID_RENT[4], mayfair.calculateRent());
        mayfair.addHotel();
        assertEquals(VALID_RENT[5], mayfair.calculateRent());
    }

    @Test(expected = IllegalAccessException.class)
    public void cannotCalculateRentWhenNotOwned() throws Exception {
        mayfair.calculateRent();
    }

    @Test
    public void canGetBuildingCost() throws Exception {
        assertEquals(BUILDING_COST, mayfair.getBuildingCost());
    }

    @Test
    public void canGetSetColour() throws Exception {
        assertEquals(COLOUR, mayfair.getSetColour());
    }

}
