/**
 * 
 */
package com.kb.monopoly.turn;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.kb.monoploy.turn.Turn;
import com.kb.monoploy.turn.Turn.TurnState;
import com.kb.monopoly.player.Player;

/**
 * @author kylebennett
 *
 */
public class TurnTest
{
    private Turn testTurn;
    private Player bob;

    @Before
    public void setup()
    {
        bob = new Player("Bob");
        testTurn = new Turn(bob);
    }

    @Test
    public void canStartTurn() throws Exception
    {
        testTurn.takeTurn();

        assertEquals(testTurn.getTurnState(), TurnState.ENDED);
        assertEquals(testTurn.getPlayer(), bob);
    }

    @Test
    public void canGetPreMoveAvailableActions()
    {
        assertEquals(testTurn.getTurnState(), TurnState.PRE_MOVE);
        testTurn.getAvailableActions();

        // TODO : Assert Actions
    }

    @Test
    public void canGetPostMoveAvailableActions()
    {
        testTurn.setTurnState(TurnState.POST_MOVE);
        assertEquals(testTurn.getTurnState(), TurnState.POST_MOVE);

        testTurn.getAvailableActions();

        // TODO : Assert Actions
    }
}
