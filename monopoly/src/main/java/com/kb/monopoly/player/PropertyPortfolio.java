/**
 * 
 */
package com.kb.monopoly.player;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.kb.monopoly.board.space.Property;

/**
 * @author kylebennett
 */
public class PropertyPortfolio
{
    private static final Logger LOG = Logger.getLogger(PropertyPortfolio.class);

    private final Player player;
    private final List<Property> portfolio = new ArrayList<Property>();

    public PropertyPortfolio(final Player player)
    {
        this.player = player;
    }

    /**
     * Buy a property, add it to inventory provided the player can afford it and
     * it is not already owned by another player.
     * 
     * @param property
     *            - the property to buy.
     */
    public void buy(final Property property)
    {
        if (property.getOwner() == null)
        {
            try
            {
                player.pay(property.getValue(), false);
                portfolio.add(property);
                property.setOwner(player);

                LOG.info("[" + player + "] bought [" + property + "]");

            }
            catch (final IllegalArgumentException iae)
            {

                LOG.error("Can't buy [" + property + "] - Not enough money!");

                throw new IllegalArgumentException("Cannot buy [" + property + "] - Not enough money", iae);
            }
        }
        else
        {
            throw new IllegalStateException("Cannot buy [" + property + "] - Already Owned by [" + property.getOwner() + "]");
        }
    }

    /**
     * Mortgage a property owned by the player, player receives the mortgage
     * value of the property.
     * 
     * @param property
     *            - the property to mortgage.
     */
    public void mortgage(final Property property)
    {
        if (portfolio.contains(property))
        {
            if (!property.isMortgaged())
            {
                property.mortgage(true);
                player.receive(property.getMortgageValue());
            }
            else
            {
                throw new IllegalStateException(property.getName() + " has already been mortgaged.");
            }
        }
        else
        {
            throw new IllegalStateException("Cannot mortgage a Property you don't own.");
        }
    }

    /**
     * Un-mortgage a mortgaged property. Player must pay the mortgage value of
     * the property.
     * 
     * @param property
     *            - property to unmortgage.
     */
    public void unmortgage(final Property property)
    {
        if (portfolio.contains(property))
        {
            if (property.isMortgaged())
            {
                try
                {
                    player.pay(property.getMortgageValue(), false);
                    property.mortgage(false);
                }
                catch (final IllegalArgumentException iae)
                {
                    throw new IllegalArgumentException("Insufficient funds to unmortgage " + property.getName(), iae);
                }
            }
            else
            {
                throw new IllegalStateException(property.getName() + " has not been mortgaged.");
            }
        }
        else
        {
            throw new IllegalStateException("Cannot unmortgage a Property you don't own.");
        }
    }

    public List<Property> getProperties()
    {
        return portfolio;
    }

}
