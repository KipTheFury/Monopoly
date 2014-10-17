/**
 * 
 */
package com.kb.monopoly.board;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author kbennett
 * 
 */
public class BoardTest
{

    private Board board;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {

        final BoardLoader mockBoardLoader = Mockito.mock(BoardLoader.class);

        board = new Board(mockBoardLoader);
    }

    @Test
    public void boardHas40spaces() throws Exception
    {

        assertEquals(40, board.getSpaces().size());
    }

    @Test
    public void boardHas16ChanceCards() throws Exception
    {

        assertEquals(16, board.getChanceCards().size());
    }

    @Test
    public void boardHas16CommunityChestCards() throws Exception
    {

        assertEquals(16, board.getCommunityChestCards().size());
    }

    @Test
    public void boardHas32Houses() throws Exception
    {
        assertEquals(32, board.getHouses());
    }

    @Test
    public void boardHas12Hotels() throws Exception
    {
        assertEquals(12, board.getHotels());
    }

    @Test
    public void firstSpaceIsGo() throws Exception
    {

        assertEquals("Go", board.getSpace(Board.GO).getName());
    }
}
