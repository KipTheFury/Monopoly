/**
 * 
 */
package com.kb.monopoly.board;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * @author kylebennett
 *
 */
public class CardLoader
{
    private static final int CARD_COUNT = 16;

    private static final Logger LOG = Logger.getLogger(CardLoader.class);

    /**
     * Load the Chance Cards.
     */
    public List<String> loadChanceCards()
    {
        LOG.info("Loading Chance Cards...");

        final List<String> chanceCards = new ArrayList<String>();

        for (int j = 0; j < CARD_COUNT; j++)
        {
            chanceCards.add("ChanceCard " + j);
        }

        return chanceCards;
    }

    /**
     * Load the Community Chest Cards.
     */
    public List<String> loadCommunityChestCards()
    {
        LOG.info("Loading Community Chest Cards...");

        final List<String> communityChestCards = new ArrayList<String>();

        for (int j = 0; j < CARD_COUNT; j++)
        {
            communityChestCards.add("CommunityChestCard " + j);
        }

        return communityChestCards;
    }

}
