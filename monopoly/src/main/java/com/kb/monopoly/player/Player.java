/**
 * 
 */
package com.kb.monopoly.player;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.kb.monopoly.board.Board;
import com.kb.monopoly.board.Ownable;

/**
 * A player.
 * 
 * @author kbennett
 * 
 */
public class Player {

    private static final Logger log = Logger.getLogger(Player.class);

    private final String name;
    private int currentBalance;
    private int currentSpace;

    private final ArrayList<Ownable> inventory = new ArrayList<Ownable>();

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
     * @return
     */
    public ArrayList<Ownable> getInventory() {
        return inventory;
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
            log.error("Invalid Roll");
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
     * @param index
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

    /**
     * Pay money to the bank. The player cannot optionally pay more than their current balance.
     * Paying a fine or rent to another player must be paid and can take the players balance below
     * zero.
     * 
     * @param amount
     *            - Amount to pay.
     * @param compulsory
     *            - fines and rent must be paid.
     */
    public void pay(int amount, boolean compulsory) throws IllegalArgumentException {

        if (amount > 0) {

            if (compulsory) {
                log.info("[" + name + "] paid [" + amount + "] to the bank.");

                currentBalance -= amount;
            } else {

                if ((currentBalance - amount) > 0) {
                    log.info("[" + name + "] paid [" + amount + "] to the bank.");

                    currentBalance -= amount;
                } else {
                    throw new IllegalArgumentException("Not enough money to complete the transaction");
                }
            }

        } else {
            throw new IllegalArgumentException("Invalid Amount");
        }
    }

    /**
     * Pay another player an amount of money.
     * 
     * @param otherPlayer
     *            - player to give the money to.
     * @param amount
     *            - amount to pay.
     * @param compulsory
     *            - fines and rent must be paid.
     */
    public void pay(Player otherPlayer, int amount, boolean compulsory) {
        pay(amount, compulsory);
        otherPlayer.receive(amount);
    }

    /**
     * Player receives an amount of money.
     * 
     * @param amount
     *            - Amount to receive.
     */
    public void receive(int amount) {

        log.info("[" + name + "] received [" + amount + "]");

        currentBalance += amount;
    }

    /**
     * @param property
     */
    public void buy(Ownable property) {

        if (property.getOwner() == null) {
            try {
                pay(property.getValue(), false);
                inventory.add(property);
                property.setOwner(this);

                log.info("[" + name + "] bought [" + property + "]");

            } catch (IllegalArgumentException iae) {

                log.error("Can't buy [" + property + "] - Not enough money!");

                throw new IllegalArgumentException("Cannot buy [" + property + "] - Not enough money", iae);
            }
        } else {
            throw new IllegalStateException("Cannot buy [" + property + "] - Already Owned by [" + property.getOwner()
                    + "]");
        }
    }

    /**
     * @param property
     */
    public void mortgage(Ownable property) {

        if (inventory.contains(property)) {

            if (!property.isMortgaged()) {

                property.mortgage(true);
                receive(property.getMortgageValue());

            } else {
                throw new IllegalStateException(property.getName() + " has already been mortgaged.");
            }
        } else {
            throw new IllegalStateException("Cannot mortgage a Property you don't own.");
        }
    }

    /**
     * @param property
     */
    public void unmortgage(Ownable property) {

        if (inventory.contains(property)) {

            if (property.isMortgaged()) {

                try {
                    pay(property.getMortgageValue(), false);
                    property.mortgage(false);
                } catch (IllegalArgumentException iae) {
                    throw new IllegalArgumentException("Insufficient funds to unmortgage " + property.getName(), iae);
                }

            } else {
                throw new IllegalStateException(property.getName() + " has not been mortgaged.");
            }
        } else {
            throw new IllegalStateException("Cannot unmortgage a Property you don't own.");
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return name;
    }

}
