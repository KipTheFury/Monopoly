/**
 * 
 */
package com.kb.monopoly.turn;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.kb.monopoly.game.DiceRoller;
import com.kb.monopoly.game.Game;
import com.kb.monopoly.player.Player;
import com.kb.monopoly.turn.Turn.TurnAction;
import com.kb.monopoly.turn.Turn.TurnState;

/**
 * @author kylebennett
 *
 */
public class TurnTest
{
    private Turn testTurn;
    private Player bob;
    private Game mockGame;

    @Before
    public void setup()
    {
        bob = mock(Player.class);
        mockGame = mock(Game.class);
        testTurn = new Turn(bob, mockGame);
    }

    @Test
    public void canStartTurn() throws Exception
    {
        assertEquals(testTurn.getTurnState(), TurnState.PRE_MOVE);
        assertEquals(testTurn.getPlayer(), bob);
    }

    @Test
    public void canGetPreMoveAvailableActions()
    {
        assertEquals(testTurn.getTurnState(), TurnState.PRE_MOVE);

        final List<TurnAction> actions = testTurn.getAvailableActions();

        assertThat(actions, hasSize(3));

        assertThat(actions, hasItem(TurnAction.MOVE));
        assertThat(actions, hasItem(TurnAction.INITIATE_TRADE));
        assertThat(actions, hasItem(TurnAction.PROPERTY_ACTIONS));
    }

    @Test
    public void canGetPreMoveAvailableActionsWhenPlayerIsJailedAndCantAffordBail()
    {
        when(bob.isJailed()).thenReturn(true);
        when(bob.getGetOutOfJailFreeCards()).thenReturn(0);
        when(bob.getCurrentBalance()).thenReturn(49);
        assertEquals(testTurn.getTurnState(), TurnState.PRE_MOVE);

        final List<TurnAction> actions = testTurn.getAvailableActions();

        assertThat(actions, hasSize(1));

        assertThat(actions, hasItem(TurnAction.ROLL_FOR_RELEASE));
    }

    @Test
    public void canGetPreMoveAvailableActionsWhenPlayerIsJailedAndHasNoGetOutOFJailFreeCards()
    {
        when(bob.isJailed()).thenReturn(true);
        when(bob.getGetOutOfJailFreeCards()).thenReturn(0);
        when(bob.getCurrentBalance()).thenReturn(1500);
        assertEquals(testTurn.getTurnState(), TurnState.PRE_MOVE);

        final List<TurnAction> actions = testTurn.getAvailableActions();

        assertThat(actions, hasSize(2));

        assertThat(actions, hasItem(TurnAction.ROLL_FOR_RELEASE));
        assertThat(actions, hasItem(TurnAction.PAY_BAIL));
    }

    @Test
    public void canGetPreMoveAvailableActionsWhenPlayerIsJailedAndHasGetOutOFJailFreeCards()
    {
        when(bob.isJailed()).thenReturn(true);
        when(bob.getGetOutOfJailFreeCards()).thenReturn(2);
        when(bob.getCurrentBalance()).thenReturn(1500);
        assertEquals(testTurn.getTurnState(), TurnState.PRE_MOVE);

        final List<TurnAction> actions = testTurn.getAvailableActions();

        assertThat(actions, hasSize(3));

        assertThat(actions, hasItem(TurnAction.ROLL_FOR_RELEASE));
        assertThat(actions, hasItem(TurnAction.GET_OUT_OF_JAIL_FREE));
        assertThat(actions, hasItem(TurnAction.PAY_BAIL));
    }

    @Test
    public void canGetPostMoveAvailableActions()
    {
        testTurn.setTurnState(TurnState.POST_MOVE);
        assertEquals(testTurn.getTurnState(), TurnState.POST_MOVE);

        final List<TurnAction> actions = testTurn.getAvailableActions();

        assertThat(actions, hasSize(3));

        assertThat(actions, hasItem(TurnAction.END_TURN));
        assertThat(actions, hasItem(TurnAction.INITIATE_TRADE));
        assertThat(actions, hasItem(TurnAction.PROPERTY_ACTIONS));
    }

    @Test
    public void canMove() throws Exception
    {
        final DiceRoller mockDice = mock(DiceRoller.class);
        Game.dice = mockDice;

        when(mockDice.roll()).thenReturn(6, 1);

        testTurn.performAction(TurnAction.MOVE);

        verify(bob).move(7);
    }

    @Test
    public void playerIsJailedAfter3Doubles() throws Exception
    {
        final DiceRoller mockDice = mock(DiceRoller.class);
        Game.dice = mockDice;

        when(mockDice.roll()).thenReturn(1, 1, 1, 1, 1, 1);

        testTurn.performAction(TurnAction.MOVE);

        verify(bob, times(3)).move(2);
        verify(bob).goToJail();
    }

    @Test
    public void canEndTurn() throws Exception
    {
        testTurn.performAction(TurnAction.END_TURN);

        verify(mockGame).endTurn();
    }

    @Test
    public void canPayBail() throws Exception
    {
        testTurn.performAction(TurnAction.PAY_BAIL);

        verify(bob).payBail();
    }

    @Test
    public void canRollForRelease() throws Exception
    {
        final DiceRoller mockDice = mock(DiceRoller.class);
        Game.dice = mockDice;

        when(mockDice.roll()).thenReturn(3, 2);

        testTurn.performAction(TurnAction.ROLL_FOR_RELEASE);

        verify(mockDice, times(2)).roll();

        verify(mockGame).endTurn();
    }

    @Test
    public void canRollForReleaseAndIsReleased() throws Exception
    {
        final DiceRoller mockDice = mock(DiceRoller.class);
        Game.dice = mockDice;

        when(mockDice.roll()).thenReturn(3, 3);

        testTurn.performAction(TurnAction.ROLL_FOR_RELEASE);

        verify(mockDice, times(2)).roll();
        verify(bob).setJailed(false);
        verify(mockGame).endTurn();
    }

    @Test
    public void canUseGetOutOfJailFreeCards() throws Exception
    {
        testTurn.performAction(TurnAction.GET_OUT_OF_JAIL_FREE);

        verify(bob).useGetOutOfJailFreeCard();
    }

    @Test
    public void canInitiateTrade() throws Exception
    {
        testTurn.performAction(TurnAction.INITIATE_TRADE);
    }

    @Test
    public void canPerformPropertyActions() throws Exception
    {
        testTurn.performAction(TurnAction.PROPERTY_ACTIONS);
    }
}
