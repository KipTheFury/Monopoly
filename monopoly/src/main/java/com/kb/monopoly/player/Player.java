/**
 * 
 */
package com.kb.monopoly.player;

/**
 * A player.
 * 
 * @author kbennett
 * 
 */
public class Player {

    private String name;
    private int currentBalance;

    /**
     * Constructor
     * 
     * @param name
     *            - the player's name.
     */
    public Player(String name) {
        this.name = name;
        this.currentBalance = 1500;
    }

    /**
     * Get the Player's Name.
     * 
     * @return - The player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the current amount of money held by the player.
     * 
     * @return - current balance.
     */
    public int getCurrentBalance() {

        return currentBalance;
    }

}
