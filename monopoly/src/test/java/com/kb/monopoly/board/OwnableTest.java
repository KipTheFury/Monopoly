/**
 * com.kb.monopoly.board.OwnableTest.java
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
public class OwnableTest {

    private Ownable kingsCross;

    @Before
    public void setUp() throws Exception {
        kingsCross = new Ownable("Kings Cross Station", 200);
    }

    @Test
    public void canGetValue() throws Exception {
        assertEquals(200, kingsCross.getValue());
    }

}
