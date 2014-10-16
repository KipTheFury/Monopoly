/**
 * 
 */
package com.kb.monopoly.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.kb.monopoly.board.space.Property;
import com.kb.monopoly.board.space.Station;

/**
 * @author kbennett
 * 
 */
public class PlayerTest
{

    private Player bob;
    private Player jane;

    private Property kingscross;

    @Before
    public void Setup()
    {
        bob = new Player("Bob");
        jane = new Player("Jane");

        kingscross = new Station("Kings Cross");
    }

    @Test
    public void canCreatePlayerCalledBob() throws Exception
    {
        assertEquals("Bob", bob.getName());
    }

    @Test
    public void playerStartsWith1500Money() throws Exception
    {
        assertEquals(1500, bob.getCurrentBalance());
    }

    @Test
    public void startsAtGo() throws Exception
    {
        assertEquals(0, bob.getCurrentSpace());
    }

    @Test
    public void canMove() throws Exception
    {
        bob.move(3);
        assertEquals(3, bob.getCurrentSpace());
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotMoveLessThan2() throws Exception
    {
        bob.move(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotMoveMoreThan12() throws Exception
    {
        bob.move(14);
    }

    @Test
    public void canMoveToSpecificSpace() throws Exception
    {
        bob.moveTo(15);
        assertEquals(15, bob.getCurrentSpace());
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotMoveToInvalidSpace_negativeIndex() throws Exception
    {
        bob.moveTo(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotMoveToInvalidSpace_indexGreaterThan39() throws Exception
    {
        bob.moveTo(53);
    }

    @Test
    public void canPassGo() throws Exception
    {
        passGo(10);

        assertEquals(9, bob.getCurrentSpace());
    }

    @Test
    public void gets200forPassingGo() throws Exception
    {

        final int balanceBeforePassingGo = bob.getCurrentBalance();

        passGo(7);

        assertEquals(balanceBeforePassingGo + 200, bob.getCurrentBalance());
    }

    private void passGo(final int roll)
    {
        bob.moveTo(39);
        bob.move(roll);
    }

    @Test
    public void canGetGetOutOfJailFreeCards() throws Exception
    {
        bob.setGetOutOfJailFreeCards(1);
        assertEquals(1, bob.getGetOutOfJailFreeCards());
    }

    @Test
    public void canGetIsJailed() throws Exception
    {
        bob.setJailed(true);
        assertTrue(bob.isJailed());
    }

    @Test
    public void canPayBank() throws Exception
    {
        final int beforeBalance = bob.getCurrentBalance();
        bob.pay(300, false);
        assertEquals(beforeBalance - 300, bob.getCurrentBalance());
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotPayBankLessThanZero() throws Exception
    {
        bob.pay(-100, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotPayBankMoreThanBalance_NonCompulsoryPayment() throws Exception
    {
        bob.pay(bob.getCurrentBalance() + 100, false);
    }

    @Test
    public void canPayBankMoreThanBalance_CompulsoryPayment() throws Exception
    {
        bob.pay(bob.getCurrentBalance() + 100, true);
        assertEquals(-100, bob.getCurrentBalance());
    }

    @Test
    public void canReceiveMoney()
    {
        final int beforeBalance = bob.getCurrentBalance();
        bob.receive(100);
        assertEquals(beforeBalance + 100, bob.getCurrentBalance());
    }

    @Test
    public void canPayPlayer() throws Exception
    {
        final int bobBeforeBalance = bob.getCurrentBalance();
        final int janeBeforeBalance = jane.getCurrentBalance();

        bob.pay(jane, 100, false);

        assertEquals(bobBeforeBalance - 100, bob.getCurrentBalance());
        assertEquals(janeBeforeBalance + 100, jane.getCurrentBalance());
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotPayPlayerMoreThanCurrentBalance_NonCompulsoryPayment() throws Exception
    {
        bob.pay(jane, bob.getCurrentBalance() + 100, false);
    }

    @Test
    public void canPayPlayerMoreThanCurrentBalance_CompulsoryPayment() throws Exception
    {

        final int bobBeforeBalance = bob.getCurrentBalance();
        final int janeBeforeBalance = jane.getCurrentBalance();

        final int paymentAmount = bobBeforeBalance + 100;

        bob.pay(jane, paymentAmount, true);

        assertEquals(bobBeforeBalance - paymentAmount, bob.getCurrentBalance());
        assertEquals(janeBeforeBalance + paymentAmount, jane.getCurrentBalance());
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotPayPlayerLessThanZero() throws Exception
    {
        bob.pay(jane, -100, false);
    }
}
