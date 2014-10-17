/**
 * 
 */
package com.kb.monopoly.player;

import org.apache.log4j.Logger;

import com.kb.monopoly.board.Board;
import com.kb.monopoly.board.space.property.PropertyPortfolio;

/**
 * A player.
 * 
 * @author kbennett
 * 
 */
public class Player
{
    private static final int INITIAL_BALANCE = 1500;
    private static final int GO_FEE = 200;
    private static final int BAIL = 50;

    private static final int MAX_ROLL = 12;
    private static final int MIN_ROLL = 2;

    private static final Logger LOG = Logger.getLogger(Player.class);

    private final String name;
    private int currentBalance;
    private int currentSpace;

    private boolean jailed = false;
    private int getOutOfJailFreeCards = 0;

    private final PropertyPortfolio propertyPortfolio;

    /**
     * Constructor.
     * 
     * @param name
     *            - the player's name.
     */
    public Player(final String name)
    {
        this.name = name;
        currentBalance = INITIAL_BALANCE;
        currentSpace = 0;

        propertyPortfolio = new PropertyPortfolio(this);
    }

    /**
     * Get the Player's Name.
     * 
     * @return - The player's name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get the current amount of money held by the player.
     * 
     * @return - current balance.
     */
    public int getCurrentBalance()
    {

        return currentBalance;
    }

    /**
     * Get the index of the player's current position on the board.
     * 
     * @return - index of current position.
     */
    public int getCurrentSpace()
    {

        return currentSpace;
    }

    /**
     * Return a list of properties owned by this player.
     * 
     * @return
     */
    public PropertyPortfolio getPortfolio()
    {
        return propertyPortfolio;
    }

    /**
     * Returns true if the player has been jailed.
     * 
     * @return
     */
    public boolean isJailed()
    {
        return jailed;
    }

    /**
     * Set true when the player has been jailed.
     * 
     * @param jailed
     */
    public void setJailed(final boolean jailed)
    {
        this.jailed = jailed;
    }

    /**
     * Get the number of Get Out Of Jail Free cards currently held by the
     * player.
     * 
     * @return
     */
    public int getGetOutOfJailFreeCards()
    {
        return getOutOfJailFreeCards;
    }

    /**
     * Set the number of Get Out Of Jail Free cards the player is holding.
     * 
     * @param getOutOfJailFreeCards
     */
    public void setGetOutOfJailFreeCards(final int getOutOfJailFreeCards)
    {
        this.getOutOfJailFreeCards = getOutOfJailFreeCards;
    }

    /**
     * Move forward a given number of spaces. If the move would move them past
     * the 39th space, they have effectively passed Go and are given 200.
     * 
     * @param roll
     *            - the number of spaces to move
     */
    public void move(final int roll)
    {
        if (roll < MIN_ROLL || roll > MAX_ROLL)
        {
            LOG.error("Invalid Roll");
            throw new IllegalArgumentException("Invalid Roll");
        }

        if (currentSpace + roll <= Board.MAX_SPACE_INDEX)
        {
            currentSpace += roll;
        }
        else
        {

            LOG.info("[" + name + "] passed Go! Collect " + GO_FEE);

            currentSpace = (currentSpace + roll) - (Board.MAX_SPACE_INDEX + 1);
            currentBalance += GO_FEE;

        }
    }

    /**
     * Move to a specific space on the board.
     * 
     * @param index
     *            - the index of the space.
     */
    public void moveTo(final int index)
    {

        if (index > 0 && index <= Board.MAX_SPACE_INDEX)
        {
            currentSpace = index;
        }
        else
        {
            throw new IllegalArgumentException("Invalid Space");
        }
    }

    /**
     * Pay money to the bank. The player cannot optionally pay more than their
     * current balance. Paying a fine or rent to another player must be paid and
     * can take the players balance below zero.
     * 
     * @param amount
     *            - Amount to pay.
     * @param compulsory
     *            - fines and rent must be paid.
     */
    public void pay(final int amount, final boolean compulsory)
    {
        if (amount > 0)
        {
            if (compulsory)
            {
                LOG.info("[" + name + "] paid [" + amount + "] to the bank.");

                currentBalance -= amount;
            }
            else
            {
                if ((currentBalance - amount) > 0)
                {
                    LOG.info("[" + name + "] paid [" + amount + "] to the bank.");

                    currentBalance -= amount;
                }
                else
                {
                    throw new IllegalArgumentException("Not enough money to complete the transaction");
                }
            }
        }
        else
        {
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
    public void pay(final Player otherPlayer, final int amount, final boolean compulsory)
    {
        pay(amount, compulsory);
        otherPlayer.receive(amount);
    }

    /**
     * Player receives an amount of money.
     * 
     * @param amount
     *            - Amount to receive.
     */
    public void receive(final int amount)
    {
        LOG.info("[" + name + "] received [" + amount + "]");

        currentBalance += amount;
    }

    /**
     * Use a Get Out Of Jail Free card.
     */
    public void useGetOutOfJailFreeCard()
    {
        this.jailed = false;
        this.getOutOfJailFreeCards--;
    }

    public void payBail()
    {
        pay(BAIL, true);
        this.jailed = false;
    }

    public void goToJail()
    {
        moveTo(Board.JAIL);
        this.jailed = true;

    }

    @Override
    public String toString()
    {
        return name;
    }
}
