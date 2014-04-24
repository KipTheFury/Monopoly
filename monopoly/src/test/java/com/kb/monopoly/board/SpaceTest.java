/**
 * com.kb.monopoly.board.SpaceTest.java
 *
 * Created - 22 Apr 2014
 * Last Updated - 22 Apr 2014
 */
package com.kb.monopoly.board;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Kyle
 * 
 */
public class SpaceTest {

    private Space go;

    @Before
    public void setUp() throws Exception {
        go = new Space("Go");
    }

    @Test
    public void canGetSpaceName() throws Exception {

        assertEquals("Go", go.getName());
    }
}
