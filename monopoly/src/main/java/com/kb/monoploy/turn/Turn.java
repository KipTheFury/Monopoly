/**
 * 
 */
package com.kb.monoploy.turn;

import org.apache.log4j.Logger;

import com.kb.monopoly.player.Player;

/**
 * @author kylebennett
 *
 */
public class Turn
{
    private static final Logger LOG = Logger.getLogger(Turn.class);

    public static enum TurnState
    {
        PRE_MOVE, POST_MOVE, ENDED;
    }

    private final Player player;

    private TurnState turnState;

    public Turn(final Player player)
    {
        this.player = player;
        this.turnState = TurnState.PRE_MOVE;
    }

    public void takeTurn()
    {
        while (turnState != TurnState.ENDED)
        {
            endTurn();
        }
    }

    public void getAvailableActions()
    {
        switch (turnState)
        {
            case POST_MOVE:
                LOG.info("Post Move Actions");

                // Can Trade or End Turn

                break;

            case PRE_MOVE:
                LOG.info("Pre Move Actions");

                if (player.isJailed())
                {
                    if (player.getGetOutOfJailFreeCards() > 0)
                    {
                        ; // Can use Get out of Jail Free card
                    }
                    else
                    {
                        ; // Can roll for escape or pay £50
                    }
                }
                else
                {
                    ; // Can Move or Trade
                }

                break;
            default:
                throw new IllegalStateException("Illegal Turn State");
        }
    }

    public void endTurn()
    {
        turnState = TurnState.ENDED;
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
