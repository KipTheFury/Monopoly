/**
 * 
 */
package com.kb.monopoly.board.space;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.kb.monopoly.board.space.CornerSpace.CornerSpaces;

/**
 * @author kbennett
 * 
 */
public class CornerSpaceTest {

    private CornerSpace go;

    @Before
    public void setUp() {
        go = new CornerSpace("Go", CornerSpaces.GO);
    }

    @Test
    public void canGetType() throws Exception {

        final CornerSpaces c = CornerSpaces.valueOf("GO");

        assertEquals(CornerSpaces.GO, go.getType());
    }
}
