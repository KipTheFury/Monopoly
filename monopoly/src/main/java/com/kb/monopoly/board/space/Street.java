/**
 * 
 */
package com.kb.monopoly.board.space;

import java.util.ArrayList;

import com.kb.monopoly.board.PropertySets;
import com.kb.monopoly.board.PropertySets.SetColour;

/**
 * @author kbennett
 * 
 */
public class Street extends Property {

    private static final int MAX_HOUSES = 4;

    private final PropertySets.SetColour setColour;

    private int buildingCount;

    private final int buildingCost;

    private final int[] rentLevels;

    /**
     * Constructor.
     * 
     * @param name
     *            - name of the street.
     * @param value
     *            - value of the street.
     * @param mortgageValue
     *            - mortgage value of the street.
     * @param setColour
     *            - property set the street belongs to.
     * @param buildingCost
     *            - the cost of adding houses/hotels.
     * @param rent
     *            - the different levels of rent for this street.
     */
    public Street(final String name, final int value, final PropertySets.SetColour setColour, final int buildingCost,
            final int[] rent) {
        super(name, value);

        this.setColour = setColour;
        this.buildingCost = buildingCost;

        if (rent.length == 6) {

            rentLevels = rent;

        } else
            throw new IllegalArgumentException("Invalid rent levels - must have 6 rent levels");
    }

    /**
     * Add a house to this street, cannot add more than 4.
     */
    public void addHouse() {

        if (buildingCount < MAX_HOUSES) {
            buildingCount++;
        } else
            throw new IllegalStateException("Can't have more than 4 Houses per Street");
    }

    /**
     * Add a hotel to this street, only after 4 houses have been built.
     */
    public void addHotel() {

        if (buildingCount <= MAX_HOUSES) {
            if (buildingCount == MAX_HOUSES) {

                buildingCount++;

            } else
                throw new IllegalStateException("Must have 4 Houses before building a Hotel");
        } else
            throw new IllegalStateException("There is already a Hotel on this Street");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.kb.monopoly.board.Property#calculateRent()
     */
    @Override
    public int calculateRent() throws IllegalAccessException {

        if (ownedBy != null) {

            int rent = 0;

            if (undevelopedPropertySet()) {
                rent = rentLevels[0] * 2;
            } else {

                rent = rentLevels[buildingCount];
            }
            return rent;
        } else
            throw new IllegalAccessException("No-one owns " + name);
    }

    private boolean undevelopedPropertySet() {

        final ArrayList<Street> set = PropertySets.getPropertySet(setColour);

        if (ownedBy.getInventory().containsAll(set)) {
            for (final Street s : set) {
                if (s.getBuildingCount() > 0)
                    return false;
            }

            return true;
        }

        return false;
    }

    /**
     * Get the number of buildings added to this street.
     * 
     * 0-4 houses 5 Hotel
     * 
     * @return the building count.
     */
    public int getBuildingCount() {

        return buildingCount;
    }

    /**
     * Get the cost of adding a house or a hotel to the street.
     * 
     * @return - cost of adding a house/hotel.
     */
    public int getBuildingCost() {

        return buildingCost;
    }

    /**
     * Get the set this property belongs to.
     * 
     * @return - set colour.
     */
    public PropertySets.SetColour getSetColour() {
        return setColour;
    }

}
