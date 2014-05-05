/**
 * 
 */
package com.kb.monopoly.board.space;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.kb.monopoly.board.space.TaxSpace;

/**
 * @author kbennett
 * 
 */
public class TaxSpaceTest {

    private TaxSpace taxSpace;

    @Before
    public void setup() {
        taxSpace = new TaxSpace("Income Tax", 200);
    }

    @Test
    public void canGetTaxAmount() throws Exception {
        assertEquals(200, taxSpace.getTaxAmount());
    }

}
