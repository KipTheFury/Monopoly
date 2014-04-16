/**
 * 
 */
package com.kb.monopoly.board;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The game board.
 * 
 * Contains - 40 spaces - 16 Chance cards - 16 Community Cards
 * 
 * @author kbennett
 * 
 */
public class Board {

    private ArrayList<String> spaces = new ArrayList<String>(40);

    private Collection<String> chanceCards = new ArrayList<String>(16);
    private Collection<String> communityChestCards = new ArrayList<String>(16);

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
        for (int j = 0; j < 16; j++) {
            chanceCards.add("ChanceCard " + j);
            communityChestCards.add("CommunityChestCard " + j);
        }
    }

    /**
     * Load the Spaces.
     */
    private void loadSpaces() {

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

}
