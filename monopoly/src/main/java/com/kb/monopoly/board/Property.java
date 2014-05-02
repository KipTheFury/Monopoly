/**
 * com.kb.monopoly.board.Ownable.java
 *
 * Created - 22 Apr 2014
 * Last Updated - 22 Apr 2014
 */
package com.kb.monopoly.board;

import com.kb.monopoly.player.Player;

/**
 * @author Kyle
 * 
 */
public abstract class Property extends Space {

    protected final int value;
    protected Player ownedBy;
    protected final int mortgageValue;
    protected boolean mortgaged;

    /**
     * Constructor.
     * 
     * @param name
     *            - The name of the space
     * @param value
     *            - The value of the space
     */
    public Property(final String name, final int value) {
        super(name);
        this.value = value;
        mortgageValue = value / 2;
    }

    /**
     * Calculate the amount of rent that must be paid to the owner of this property.
     * 
     * Depends on different factors such as number of houses/hotels, etc depending on the type of
     * property.
     * 
     * @return The amount of rent to be paid to the owner.
     * @throws IllegalAccessException
     */
    public abstract int calculateRent() throws IllegalAccessException;

    /**
     * Get the value of the space.
     * 
     * @return - value of the space.
     */
    public int getValue() {

        return value;
    }

    /**
     * If this property has been bought by a player return the player, otherwise returns null.
     * 
     * @return - the player who owns this property.
     */
    public Player getOwner() {

        return ownedBy;
    }

    /**
     * Set the owner of this property following a purchase or a trade.
     * 
     * @param player
     *            - new owner of this property.
     */
    public void setOwner(final Player player) {

        ownedBy = player;
    }

    /**
     * Set the property's mortgage status, true to mortgage, false to un-mortgage.
     * 
     * @param mortgaged
     */
    public void mortgage(final boolean mortgaged) {
        this.mortgaged = mortgaged;

    }

    /**
     * Get the value mortgaging this property.
     * 
     * @return - the mortgage value
     */
    public int getMortgageValue() {
        return mortgageValue;
    }

    /**
     * Check whether this property has been mortgaged.
     * 
     * @return - this property's mortgage status.
     */
    public boolean isMortgaged() {
        return mortgaged;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
