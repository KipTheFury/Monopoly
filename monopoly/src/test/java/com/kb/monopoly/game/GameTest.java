/**
 * 
 */
package com.kb.monopoly.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.kb.monopoly.player.Player;

/**
 * @author kbennett
 * 
 */
public class GameTest
{

    private Game g;

    private Player bob;
    private Player jane;
    private Player fred;
    private Player sally;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
        g = new Game();

        bob = new Player("Bob");
        jane = new Player("Jane");
        fred = new Player("Fred");
        sally = new Player("Sally");

    }

    @Test
    public void gameHasBoard() throws Exception
    {
        assertNotNull(g.getBoard());
    }

    @Test
    public void canRollDie() throws Exception
    {
        for (int i = 0; i < 10; i++)
        {
            final int roll = Game.rollDie();
            assertTrue(roll >= 1 && roll <= 6);
        }
    }

    @Test
    public void canStartGame() throws Exception
    {

        g.addPlayer(bob);
        g.addPlayer(jane);

        g.startGame();
    }

    @Test
    public void canGetPlayerList() throws Exception
    {

        g.addPlayer(bob);
        g.addPlayer(jane);

        final List<Player> players = g.getPlayerList();

        assertTrue(players.contains(bob) && players.contains(jane));
    }

    @Test(expected = IllegalStateException.class)
    public void cannotStartAGameWithLessThan2Players() throws Exception
    {

        g.addPlayer(bob);
        g.startGame();
    }

    @Test(expected = IllegalStateException.class)
    public void cannotStartAGameWithMoreThan4Players() throws Exception
    {
        g.addPlayer(bob);
        g.addPlayer(jane);
        g.addPlayer(fred);
        g.addPlayer(sally);
        g.addPlayer(new Player("George"));

        g.startGame();
    }

    @Test
    public void canRollForFirstTurn() throws Exception
    {

        final DiceRoller mockDice = mock(DiceRoller.class);
        Game.dice = mockDice;

        g.addPlayer(bob);
        g.addPlayer(jane);
        g.addPlayer(fred);
        g.addPlayer(sally);

        when(mockDice.roll()).thenReturn(1, 1, 3, 1);

        g.startGame();

        assertEquals(fred, g.getCurrentPlayer());
    }

    @Test
    public void turnNumberIncrementsAtStartOfEachTurn() throws Exception
    {
        final DiceRoller mockDice = mock(DiceRoller.class);
        Game.dice = mockDice;

        g.addPlayer(bob);
        g.addPlayer(jane);

        when(mockDice.roll()).thenReturn(6, 1);

        g.startGame();

        final int currentTurnNumber = g.getCurrentTurn();

        assertEquals(bob, g.getCurrentPlayer());

        g.endTurn();
        assertEquals(jane, g.getCurrentPlayer());

        g.endTurn();
        assertEquals(bob, g.getCurrentPlayer());

        assertEquals(currentTurnNumber + 2, g.getCurrentTurn());
    }
}
