/**
 * 
 */
package com.kb.monopoly.board;

/**
 * @author kbennett
 * 
 */
public class CardSpace extends Space {

    public enum CardType {
        Chance, CommunityChest;
    }

    private final CardType cardType;

    /**
     * @param name
     */
    public CardSpace(String name, CardType cardType) {
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