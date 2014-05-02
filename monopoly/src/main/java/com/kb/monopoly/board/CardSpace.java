/**
 * 
 */
package com.kb.monopoly.board;

/**
 * @author kbennett
 * 
 */
public class CardSpace extends Space {

    /**
     * The differnt types of card that can be drawn.
     * 
     * @author kbennett
     * 
     */
    public enum CardType {
        Chance, CommunityChest;
    }

    private final CardType cardType;

    /**
     * @param name
     */
    public CardSpace(final String name, final CardType cardType) {
        super(name);
        this.cardType = cardType;
    }

    /**
     * @return the cardType
     */
    public CardType getCardType() {
        return cardType;
    }
}