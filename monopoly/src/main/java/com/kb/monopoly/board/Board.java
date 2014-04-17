/**
 * 
 */
package com.kb.monopoly.board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import org.apache.log4j.Logger;

/**
 * The game board.
 * 
 * Contains - 40 spaces - 16 Chance cards - 16 Community Cards
 * 
 * @author kbennett
 * 
 */
public class Board {

    private static final Logger log = Logger.getLogger(Board.class);

    private final ArrayList<String> spaces = new ArrayList<String>(40);

    private final Collection<String> chanceCards = new ArrayList<String>(16);
    private final Collection<String> communityChestCards = new ArrayList<String>(
            16);

    private final Random dice = new Random(System.nanoTime());

    /**
     * Constructor. Loads the Spaces and Cards
     */
    public Board() {

        loadSpaces();
        loadCards();
    }

    /**
     * Load the Chance and Community Chest Cards.
     */
    private void loadCards() {

        log.info("Loading Cards...");

        for (int j = 0; j < 16; j++) {
            chanceCards.add("ChanceCard " + j);
            communityChestCards.add("CommunityChestCard " + j);
        }
    }

    /**
     * Load the Spaces.
     */
    private void loadSpaces() {

        log.info("Loading Spaces...");

        spaces.add("Go");

        for (int i = 0; i < 39; i++) {
            spaces.add("Space " + i);
        }
    }

    /**
     * Get a specific space on the board.
     * 
     * @param index
     *            - the index of the space on the board (0-39)
     * @return - The space at the given index.
     */
    public Object getSpace(int index) {

        return spaces.get(index);
    }

    /**
     * Get a collection of all Spaces on the board.
     * 
     * @return
     */
    public Collection<String> getSpaces() {

        return spaces;
    }

    /**
     * Get all chance Cards.
     * 
     * @return
     */
    public Collection<String> getChanceCards() {

        return chanceCards;
    }

    /**
     * Get all Community Chest Cards.
     * 
     * @return
     */
    public Collection<String> getCommunityChestCards() {

        return communityChestCards;
    }

    /**
     * Roll a 6 sided dice and return the result.
     * 
     * @return - random result of a 6-sided Dice
     */
    public int rollDie() {

        log.info("Rolling Die...");

        int roll = dice.nextInt(5) + 1;

        log.info("[" + roll + "]");

        return roll;
    }
}
