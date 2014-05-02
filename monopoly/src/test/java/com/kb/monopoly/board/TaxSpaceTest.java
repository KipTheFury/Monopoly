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
