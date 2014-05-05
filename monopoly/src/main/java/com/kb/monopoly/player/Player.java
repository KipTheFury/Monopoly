/**
 * 
 */
package com.kb.monopoly.player;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.kb.monopoly.board.Board;
import com.kb.monopoly.board.space.Property;

/**
 * A player.
 * 
 * @author kbennett
 * 
 */
public class Player {

    private static final Logger LOG = Logger.getLogger(Player.class);

    private final String name;
    private int currentBalance;
    private int currentSpace;

    private final ArrayList<Property> inventory = new ArrayList<Property>();

    /**
     * Constructor.
     * 
     * @param name
     *            - the player's name.
     */
    public Player(final String name) {
        this.name = name;
        currentBalance = 1500;
        currentSpace = 0;
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
     * Return a list of properties owned by this player.
     * 
     * @return
     */
    public ArrayList<Property> getInventory() {
        return inventory;
    }

    /**
     * Move forward a given number of spaces. If the move would move them past the 39th space, they
     * have effectively passed Go and are given 200.
     * 
     * @param roll
     *            - the number of spaces to move
     */
    public void move(final int roll) {

        if (roll < 2 || roll > 12) {
            LOG.error("Invalid Roll");
            throw new IllegalArgumentException("Invalid Roll");
        }

        if (currentSpace + roll <= Board.MAX_SPACE_INDEX) {
            currentSpace += roll;
        } else {

            LOG.info("[" + name + "] passed Go! Collect 200");

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
    public void moveTo(final int index) {

        if (index > 0 && index <= Board.MAX_SPACE_INDEX) {
            currentSpace = index;
        } else
            throw new IllegalArgumentException("Invalid Space");

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
    public void pay(final int amount, final boolean compulsory) throws IllegalArgumentException {

        if (amount > 0) {

            if (compulsory) {
                LOG.info("[" + name + "] paid [" + amount + "] to the bank.");

                currentBalance -= amount;
            } else {

                if ((currentBalance - amount) > 0) {
                    LOG.info("[" + name + "] paid [" + amount + "] to the bank.");

                    currentBalance -= amount;
                } else
                    throw new IllegalArgumentException("Not enough money to complete the transaction");
            }

        } else
            throw new IllegalArgumentException("Invalid Amount");
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
    public void pay(final Player otherPlayer, final int amount, final boolean compulsory) {
        pay(amount, compulsory);
        otherPlayer.receive(amount);
    }

    /**
     * Player receives an amount of money.
     * 
     * @param amount
     *            - Amount to receive.
     */
    public void receive(final int amount) {

        LOG.info("[" + name + "] received [" + amount + "]");

        currentBalance += amount;
    }

    /**
     * Buy a property, add it to inventory provided the player can afford it and it is not already
     * owned by another player.
     * 
     * @param property
     *            - the property to buy.
     */
    public void buy(final Property property) {

        if (property.getOwner() == null) {
            try {
                pay(property.getValue(), false);
                inventory.add(property);
                property.setOwner(this);

                LOG.info("[" + name + "] bought [" + property + "]");

            } catch (final IllegalArgumentException iae) {

                LOG.error("Can't buy [" + property + "] - Not enough money!");

                throw new IllegalArgumentException("Cannot buy [" + property + "] - Not enough money", iae);
            }
        } else
            throw new IllegalStateException("Cannot buy [" + property + "] - Already Owned by [" + property.getOwner()
                    + "]");
    }

    /**
     * Mortgage a property owned by the player, player receives the mortgage value of the property.
     * 
     * @param property
     *            - the property to mortgage.
     */
    public void mortgage(final Property property) {

        if (inventory.contains(property)) {

            if (!property.isMortgaged()) {

                property.mortgage(true);
                receive(property.getMortgageValue());

            } else
                throw new IllegalStateException(property.getName() + " has already been mortgaged.");
        } else
            throw new IllegalStateException("Cannot mortgage a Property you don't own.");
    }

    /**
     * Un-mortgage a mortgaged property. Player must pay the mortgage value of the property.
     * 
     * @param property
     *            - property to unmortgage.
     */
    public void unmortgage(final Property property) {

        if (inventory.contains(property)) {

            if (property.isMortgaged()) {

                try {
                    pay(property.getMortgageValue(), false);
                    property.mortgage(false);
                } catch (final IllegalArgumentException iae) {
                    throw new IllegalArgumentException("Insufficient funds to unmortgage " + property.getName(), iae);
                }

            } else
                throw new IllegalStateException(property.getName() + " has not been mortgaged.");
        } else
            throw new IllegalStateException("Cannot unmortgage a Property you don't own.");

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
