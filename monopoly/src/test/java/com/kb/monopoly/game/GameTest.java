/**
 * 
 */
package com.kb.monopoly.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.kb.monopoly.player.Player;

/**
 * @author kbennett
 * 
 */
public class GameTest {

    private Game g;

    private Player bob;
    private Player jane;
    private Player fred;
    private Player sally;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        g = new Game();

        bob = new Player("Bob");
        jane = new Player("Jane");
        fred = new Player("Fred");
        sally = new Player("Sally");

    }

    @Test
    public void gameHasBoard() throws Exception {
        assertNotNull(g.getBoard());
    }

    @Test
    public void canRollDie() throws Exception {

        for (int i = 0; i < 10; i++) {
            int roll = g.rollDie();
            assertTrue(roll >= 1 && roll <= 6);
        }
    }

    @Test
    public void canStartGame() throws Exception {

        g.addPlayer(bob);
        g.addPlayer(jane);

        g.startGame();
    }

    @Test(expected = IllegalStateException.class)
    public void cannotStartAGameWithLessThan2Players() throws Exception {

        g.addPlayer(bob);
        g.startGame();
    }

    @Test(expected = IllegalStateException.class)
    public void cannotStartAGameWithMoreThan4Players() throws Exception {
        g.addPlayer(bob);
        g.addPlayer(jane);
        g.addPlayer(fred);
        g.addPlayer(sally);
        g.addPlayer(new Player("George"));

        g.startGame();
    }

    @Test
    @Ignore
    public void canRollForFirstTurn() throws Exception {

        g.addPlayer(bob);
        g.addPlayer(jane);
        g.addPlayer(fred);
        g.addPlayer(sally);

        when(g.rollDie()).thenReturn(1, 1, 3, 1);

        g.startGame();

        assertEquals(fred, g.getCurrentPlayer());
    }

    @Test
    public void turnNumberIncrementsAfterAllPlayersHaveEndedTurns() throws Exception {

        g.addPlayer(bob);
        g.addPlayer(jane);

        g.startGame();

        int currentTurnNumber = g.getCurrentTurn();
        g.endTurn();
        g.endTurn();

        assertEquals(currentTurnNumber + 1, g.getCurrentTurn());
    }

}
