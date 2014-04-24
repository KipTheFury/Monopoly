/**
 * 
 */
package com.kb.monopoly.board;

/**
 * @author kbennett
 * 
 */
public class Street extends Ownable {

    private static final int MAX_HOUSES = 4;

    private final PropertySets.SetColour setColour;

    private int buildingCount = 0;

    private final int buildingCost;

    private final int[] rentLevels;

    public Street(String name, int value, PropertySets.SetColour setColour, int buildingCost, int[] rent) {
        super(name, value);

        this.setColour = setColour;
        this.buildingCost = buildingCost;

        if (rent.length == 6) {

            this.rentLevels = rent;

        } else {
            throw new IllegalArgumentException("Invalid rent levels - must have 6 rent levels");
        }
    }

    /**
     * 
     */
    public void addHouse() {

        if (buildingCount < MAX_HOUSES) {
            buildingCount++;
        } else {
            throw new IllegalStateException("Can't have more than 4 Houses per Street");
        }
    }

    /**
     * 
     */
    public void addHotel() {

        if (buildingCount <= MAX_HOUSES) {
            if (buildingCount == MAX_HOUSES) {

                buildingCount++;

            } else {
                throw new IllegalStateException("Must have 4 Houses before building a Hotel");
            }
        } else {
            throw new IllegalStateException("There is already a Hotel on this Street");
        }
    }

    /**
     * @return
     */
    public int calculateRent() {

        return rentLevels[buildingCount];
    }

    /**
     * @return
     */
    public int getBuildingCount() {

        return buildingCount;
    }

    /**
     * @return
     */
    public int getBuildingCost() {

        return buildingCost;
    }

}
