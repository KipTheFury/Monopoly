/**
 * 
 */
package com.kb.monopoly.turn;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.kb.monopoly.game.Game;
import com.kb.monopoly.player.Player;

/**
 * @author kylebennett
 *
 */
public class Turn
{
    private static final int DOUBLE_COUNT_FOR_JAIL = 3;
    private static final int BAIL = 50;
    private static final Logger LOG = Logger.getLogger(Turn.class);

    public static enum TurnState
    {
        PRE_MOVE, DOUBLE_ROLL, POST_MOVE;
    }

    public static enum TurnAction
    {
        MOVE, END_TURN, INITIATE_TRADE, PROPERTY_ACTIONS, GET_OUT_OF_JAIL_FREE, ROLL_FOR_RELEASE, PAY_BAIL;
    }

    private final Player player;
    private final Game game;

    private TurnState turnState;
    private int doubleCount = 0;

    public Turn(final Player player, final Game game)
    {
        this.player = player;
        this.game = game;
        this.turnState = TurnState.PRE_MOVE;
    }

    public void performAction(final TurnAction action)
    {
        switch (action)
        {
            case END_TURN:
                endTurn();
                break;

            case INITIATE_TRADE:
                initiateTrade();
                break;

            case MOVE:
                move();
                break;

            case PROPERTY_ACTIONS:
                startPropertyActions();
                break;

            case GET_OUT_OF_JAIL_FREE:
                useGetOutOfJailFreeCard();
                break;

            case ROLL_FOR_RELEASE:
                rollForRelease();
                break;

            case PAY_BAIL:
                payBail();
                break;

            default:
                throw new IllegalArgumentException("Invalid Turn Action");

        }
    }

    public List<TurnAction> getAvailableActions()
    {
        final List<TurnAction> availableActions = new ArrayList<TurnAction>();

        switch (turnState)
        {
            case POST_MOVE:
                LOG.info("Post Move Actions");

                availableActions.add(TurnAction.END_TURN);
                availableActions.add(TurnAction.PROPERTY_ACTIONS);
                availableActions.add(TurnAction.INITIATE_TRADE);

                break;

            case PRE_MOVE:
                LOG.info("Pre Move Actions");

                if (player.isJailed())
                {
                    if (player.getGetOutOfJailFreeCards() > 0)
                    {
                        availableActions.add(TurnAction.GET_OUT_OF_JAIL_FREE);
                    }

                    availableActions.add(TurnAction.ROLL_FOR_RELEASE);

                    if (player.getCurrentBalance() > BAIL)
                    {
                        availableActions.add(TurnAction.PAY_BAIL);
                    }
                }
                else
                {
                    availableActions.add(TurnAction.MOVE);
                    availableActions.add(TurnAction.PROPERTY_ACTIONS);
                    availableActions.add(TurnAction.INITIATE_TRADE);
                }

                break;

            default:
                throw new IllegalStateException("Illegal Turn State");
        }

        return availableActions;
    }

    private void move()
    {
        final int roll1 = Game.rollDie();
        final int roll2 = Game.rollDie();

        player.move(roll1 + roll2);

        if (roll1 == roll2)
        {
            doubleCount++;
            if (doubleCount == DOUBLE_COUNT_FOR_JAIL)
            {
                player.goToJail();
            }
            else
            {
                move();
            }
        }
    }

    private void payBail()
    {
        player.payBail();
        turnState = TurnState.POST_MOVE;
    }

    private void rollForRelease()
    {
        final int roll1 = Game.rollDie();
        final int roll2 = Game.rollDie();

        if (roll1 == roll2)
        {
            player.setJailed(false);
        }

        endTurn();
    }

    private void useGetOutOfJailFreeCard()
    {
        player.useGetOutOfJailFreeCard();
        turnState = TurnState.POST_MOVE;
    }

    private void startPropertyActions()
    {

    }

    private void initiateTrade()
    {

    }

    public void endTurn()
    {
        game.endTurn();
    }

    public TurnState getTurnState()
    {
        return turnState;
    }

    public void setTurnState(final TurnState turnState)
    {
        this.turnState = turnState;
    }

    public Player getPlayer()
    {
        return player;
    }
}
