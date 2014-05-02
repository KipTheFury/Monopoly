/**
 * 
 */
package com.kb.monopoly.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.collections4.list.FixedSizeList;
import org.apache.log4j.Logger;

import com.kb.monopoly.board.space.Space;

/**
 * The game board.
 * 
 * Contains - 40 spaces - 16 Chance cards - 16 Community Cards
 * 
 * @author kbennett
 * 
 */
public class Board {

    private static final Logger LOG = Logger.getLogger(Board.class);

    public static final int GO = 0;
    public static final int JAIL = 10;
    public static final int FREE_PARKING = 20;
    public static final int GO_TO_JAIL = 30;

    public static final int MAX_SPACE_INDEX = 39;

    private FixedSizeList<Space> spaces;

    private final Collection<String> chanceCards = new ArrayList<String>(16);
    private final Collection<String> communityChestCards = new ArrayList<String>(
            16);

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

        LOG.info("Loading Cards...");

        for (int j = 0; j < 16; j++) {
            chanceCards.add("ChanceCard " + j);
            communityChestCards.add("CommunityChestCard " + j);
        }
    }

    /**
     * Load the Spaces.
     */
    private void loadSpaces() {

        LOG.info("Loading Spaces...");

        final Space[] spacesArray = new Space[40];

        spacesArray[GO] = new Space("Go");
        spacesArray[JAIL] = new Space("Jail");
        spacesArray[FREE_PARKING] = new Space("Free Parking");
        spacesArray[GO_TO_JAIL] = new Space("Go to Jail");

        for (int i = 0; i < 39; i++) {

            if (spacesArray[i] == null) {
                spacesArray[i] = new Space(String.valueOf(i));
            }
        }

        spaces = FixedSizeList.fixedSizeList(Arrays.asList(spacesArray));
    }

    /**
     * Get a specific space on the board.
     * 
     * @param index
     *            - the index of the space on the board (0-39)
     * @return - The space at the given index.
     */
    public Space getSpace(final int index) {

        return spaces.get(index);
    }

    /**
     * Get a collection of all Spaces on the board.
     * 
     * @return - Collection of all spaces
     */
    public Collection<Space> getSpaces() {

        return spaces;
    }

    /**
     * Get all chance Cards.
     * 
     * @return - Collection of all Chance cards.
     */
    public Collection<String> getChanceCards() {

        return chanceCards;
    }

    /**
     * Get all Community Chest Cards.
     * 
     * @return - Collection of Community Chest cards.
     */
    public Collection<String> getCommunityChestCards() {

        return communityChestCards;
    }

}
