/**
 * 
 */
package com.kb.monopoly.player;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @author kbennett
 * 
 */
public class PlayerTest {

    private Player bob;

    @Before
    public void Setup() {
        bob = new Player("Bob");
    }

    @Test
    public void canCreatePlayerCalledBob() throws Exception {

        assertEquals("Bob", bob.getName());
    }

    @Test
    public void playerStartsWith1500Money() throws Exception {

        assertEquals(1500, bob.getCurrentBalance());
    }
}
