/**
 * 
 */
package com.kb.monopoly.board.space;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.kb.monopoly.board.space.CardSpace.CardType;

/**
 * @author kbennett
 * 
 */
public class CardSpaceTest
{

    private CardSpace chance;
    private CardSpace communityChest;

    @Before
    public void setUp()
    {
        chance = new CardSpace("Chance", CardType.Chance);
        communityChest = new CardSpace("Community Chest", CardType.CommunityChest);
    }

    @Test
    public void canGetCardType() throws Exception
    {
        assertEquals(CardType.Chance, chance.getCardType());
        assertEquals(CardType.CommunityChest, communityChest.getCardType());
    }
}
