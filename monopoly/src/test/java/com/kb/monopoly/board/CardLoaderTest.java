/**
 * 
 */
package com.kb.monopoly.board;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author kylebennett
 *
 */
public class CardLoaderTest
{
    private CardLoader cardLoader;

    @Before
    public void setup()
    {
        cardLoader = new CardLoader();
    }

    @Test
    public void canLoadChanceCards() throws Exception
    {
        final List<String> chanceCards = cardLoader.loadChanceCards();

        assertThat(chanceCards, hasSize(16));
    }

    @Test
    public void canLoadCommunityChestCards() throws Exception
    {
        final List<String> communityChestCards = cardLoader.loadCommunityChestCards();

        assertThat(communityChestCards, hasSize(16));
    }
}
