/**
 * 
 */
package com.kb.monopoly.board;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author kbennett
 * 
 */
public class Board {

    private ArrayList<String> spaces = new ArrayList<String>(40);

    private Collection<String> chanceCards = new ArrayList<String>(16);
    private Collection<String> communityChestCards = new ArrayList<String>(16);

    public Board() {

        loadSpaces();
        loadCards();
    }

    private void loadCards() {
        for (int j = 0; j < 16; j++) {
            chanceCards.add("ChanceCard " + j);
            communityChestCards.add("CommunityChestCard " + j);
        }
    }

    private void loadSpaces() {

        spaces.add("Go");

        for (int i = 0; i < 39; i++) {
            spaces.add("Space " + i);
        }
    }

    public Object getSpace(int index) {

        return spaces.get(index);
    }

    public Collection<String> getSpaces() {
        return spaces;
    }

    public Collection<String> getChanceCards() {

        return chanceCards;
    }

    public Collection<String> getCommunityChestCards() {

        return communityChestCards;
    }

}
