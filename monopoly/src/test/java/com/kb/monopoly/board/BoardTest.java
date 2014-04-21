/**
 * 
 */
package com.kb.monopoly.board;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @author kbennett
 * 
 */
public class BoardTest {

    private Board b;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        b = new Board();
    }

    @Test
    public void boardHas40spaces() throws Exception {

        assertEquals(40, b.getSpaces().size());
    }

    @Test
    public void boardHas16ChanceCards() throws Exception {

        assertEquals(16, b.getChanceCards().size());
    }

    @Test
    public void boardHas16CommunityChestCards() throws Exception {

        assertEquals(16, b.getCommunityChestCards().size());
    }

    @Test
    public void firstSpaceIsGo() throws Exception {

        assertEquals("Go", b.getSpace(0));
    }
}
