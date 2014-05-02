/**
 * 
 */
package com.kb.monopoly.board;

/**
 * @author kbennett
 * 
 */
public class TaxSpace extends Space {

    private final int taxAmount;

    /**
     * @param name
     */
    public TaxSpace(String name, int taxAmount) {
        super(name);
        this.taxAmount = taxAmount;
    }

    /**
     * @return the taxAmount
     */
    public int getTaxAmount() {
        return taxAmount;
    }
}
