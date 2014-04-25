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
public class Ownable extends Space {

    private final int value;
    private Player ownedBy = null;
    private int mortgageValue;
    private boolean mortgaged;

    /**
     * Constructor.
     * 
     * @param name
     *            - The name of the space
     * @param value
     *            - The value of the space
     */
    public Ownable(String name, int value, int mortgageValue) {
        super(name);
        this.value = value;
        this.mortgageValue = mortgageValue;
    }

    /**
     * Get the value of the space
     * 
     * @return - value of the space.
     */
    public int getValue() {

        return value;
    }

    /**
     * @return
     */
    public Player getOwner() {

        return ownedBy;
    }

    /**
     * @param player
     */
    public void setOwner(Player player) {

        this.ownedBy = player;
    }

    public void mortgage(boolean mortgaged) {
        this.mortgaged = mortgaged;

    }

    public int getMortgageValue() {
        return mortgageValue;
    }

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
        return super.toString() + " (" + value + ")";
    }
}
