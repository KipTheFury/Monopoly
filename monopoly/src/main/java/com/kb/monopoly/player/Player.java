/**
 * 
 */
package com.kb.monopoly.player;

import org.apache.log4j.Logger;

import com.kb.monopoly.board.Board;

/**
 * A player.
 * 
 * @author kbennett
 * 
 */
public class Player {

    private static final Logger log = Logger.getLogger(Player.class);

    private String name;
    private int currentBalance;
    private int currentSpace;

    /**
     * Constructor
     * 
     * @param name
     *            - the player's name.
     */
    public Player(String name) {
        this.name = name;
        this.currentBalance = 1500;
        this.currentSpace = 0;
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

    /**
     * Get the index of the player's current position on the board.
     * 
     * @return - index of current position.
     */
    public int getCurrentSpace() {

        return currentSpace;
    }

    /**
     * Move forward a given number of spaces. If the move would move them past the 39th space, they
     * have effectively passed Go and are given 200.
     * 
     * @param roll
     *            - the number of spaces to move
     */
    public void move(int roll) {

        if (roll < 2 || roll > 12) {
            throw new IllegalArgumentException("Invalid Roll");
        }

        if (currentSpace + roll <= Board.MAX_SPACE_INDEX) {
            currentSpace += roll;
        } else {

            log.info("[" + name + "] passed Go! Collect 200");

            currentSpace = (currentSpace + roll) - (Board.MAX_SPACE_INDEX + 1);
            currentBalance += 200;

        }

    }

    /**
     * Move to a specific space on the board.
     * 
     * @param i
     *            - the index of the space.
     */
    public void moveTo(int index) {

        if (index > 0 && index <= Board.MAX_SPACE_INDEX) {
            this.currentSpace = index;
        }
        else {
            throw new IllegalArgumentException("Invalid Space");
        }

    }

}
