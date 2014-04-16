/**
 * 
 */
package com.kb.monopoly.game;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.kb.monopoly.player.Player;

/**
 * @author kbennett
 * 
 */
public class GameTest {

    Game g;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        g = new Game();
    }

    @Test
    public void gameHasBoard() throws Exception {
        assertNotNull(g.getBoard());
    }

    @Test
    public void canAddPlayer() throws Exception {

        Player bob = new Player("Bob");

        g.addPlayer(bob);

        assertTrue(g.getPlayerList().contains(bob));
    }

}
