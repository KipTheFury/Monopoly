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

    @Before
    public void Setup() {

    }

    @Test
    public void canCreatePlayerCalledBob() throws Exception {
        Player bob = new Player("Bob");

        assertEquals("Bob", bob.getName());
    }
}
