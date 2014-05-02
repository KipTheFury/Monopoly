/**
 * 
 */
package com.kb.monopoly.board.space;

/**
 * @author kbennett
 * 
 */
public class CornerSpace extends Space {

    /**
     * @author kbennett
     * 
     */
    public enum CornerSpaces {
        GO, JAIL, FREEPARKING, GOTOJAIL;
    }

    private final CornerSpaces type;

    public CornerSpace(final String name, final CornerSpaces type) {
        super(name);
        this.type = type;
    }

    /**
     * @return the type
     */
    public CornerSpaces getType() {
        return type;
    }
}
