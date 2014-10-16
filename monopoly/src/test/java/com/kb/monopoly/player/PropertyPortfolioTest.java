/**
 * 
 */
package com.kb.monopoly.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.kb.monopoly.board.space.Station;

/**
 * @author kylebennett
 *
 */
public class PropertyPortfolioTest
{
    private Player bob, jane;
    private Station kingscross;

    @Before
    public void setup()
    {
        bob = new Player("Bob");
        jane = new Player("Jane");

        kingscross = new Station("Kings Cross");
    }

    @Test
    public void canBuyProperty() throws Exception
    {
        final PropertyPortfolio portfolio = bob.getPortfolio();
        final int bobBeforeBalance = bob.getCurrentBalance();

        portfolio.buy(kingscross);

        assertTrue(portfolio.getProperties().contains(kingscross));
        assertEquals(bobBeforeBalance - kingscross.getValue(), bob.getCurrentBalance());
        assertEquals(bob, kingscross.getOwner());
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotBuyProperty_insufficientFunds() throws Exception
    {
        final PropertyPortfolio portfolio = bob.getPortfolio();
        bob.pay(bob.getCurrentBalance() - 10, true);

        portfolio.buy(kingscross);
    }

    @Test(expected = IllegalStateException.class)
    public void cannotBuyProperty_alreadyOwned() throws Exception
    {
        final PropertyPortfolio portfolio = bob.getPortfolio();

        jane.getPortfolio().buy(kingscross);

        portfolio.buy(kingscross);
    }

    @Test
    public void canMortgageProperty() throws Exception
    {
        final PropertyPortfolio portfolio = bob.getPortfolio();

        portfolio.buy(kingscross);

        final int bobBeforeBalance = bob.getCurrentBalance();

        portfolio.mortgage(kingscross);

        assertEquals(bobBeforeBalance + kingscross.getMortgageValue(), bob.getCurrentBalance());

    }

    @Test(expected = IllegalStateException.class)
    public void cannotMortgagePropertyNotOwned() throws Exception
    {
        final PropertyPortfolio portfolio = bob.getPortfolio();

        portfolio.mortgage(kingscross);
    }

    @Test(expected = IllegalStateException.class)
    public void cannotMortgageMortgagedProperty() throws Exception
    {
        final PropertyPortfolio portfolio = bob.getPortfolio();

        portfolio.buy(kingscross);
        portfolio.mortgage(kingscross);

        portfolio.mortgage(kingscross);
    }

    @Test
    public void canUnmortgageProperty() throws Exception
    {
        final PropertyPortfolio portfolio = bob.getPortfolio();

        portfolio.buy(kingscross);
        portfolio.mortgage(kingscross);

        final int bobBeforeBalance = bob.getCurrentBalance();

        portfolio.unmortgage(kingscross);

        assertEquals(bobBeforeBalance - kingscross.getMortgageValue(), bob.getCurrentBalance());
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotUnmortgageProperty_insufficientFunds() throws Exception
    {
        final PropertyPortfolio portfolio = bob.getPortfolio();

        portfolio.buy(kingscross);
        portfolio.mortgage(kingscross);

        bob.pay(bob.getCurrentBalance() - 1, true);

        portfolio.unmortgage(kingscross);
    }

    @Test(expected = IllegalStateException.class)
    public void cannotUnmortgagePropertyNotOwned() throws Exception
    {
        final PropertyPortfolio portfolio = bob.getPortfolio();

        portfolio.unmortgage(kingscross);
    }

    @Test(expected = IllegalStateException.class)
    public void cannotUnmortgageNonMortgagedProperty() throws Exception
    {
        final PropertyPortfolio portfolio = bob.getPortfolio();

        portfolio.buy(kingscross);
        portfolio.unmortgage(kingscross);
    }
}
