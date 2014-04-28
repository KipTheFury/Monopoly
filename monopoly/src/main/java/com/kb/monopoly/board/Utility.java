/**
 * com.kb.monopoly.board.Utility.java
 *
 * Created - 25 Apr 2014
 * Last Updated - 25 Apr 2014
 */
package com.kb.monopoly.board;

/**
 * @author Kyle
 * 
 */
public class Utility extends Property {

    private static final int UTILITY_VALUE = 150;
    private static final int UTILITY_MORTGAGE_VALUE = 75;

    /**
     * Constructor.
     * 
     * @param name
     */
    public Utility(String name) {
        super(name, UTILITY_VALUE, UTILITY_MORTGAGE_VALUE);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.kb.monopoly.board.Ownable#calculateRent()
     */
    @Override
    public int calculateRent() throws IllegalAccessException {
        throw new UnsupportedOperationException("Rent Calculation for Utilities requires a dice roll");
    }

    /**
     * @param diceRoll
     * @return
     */
    public int calculateRent(int diceRoll) throws IllegalAccessException {

        if (ownedBy != null) {

            int utilityCount = 0;

            for (Property o : ownedBy.getInventory()) {
                if (o instanceof Utility) {
                    utilityCount++;
                }
            }

            switch (utilityCount) {
            case 1:
                return 4 * diceRoll;
            case 2:
                return 10 * diceRoll;

            default:
                throw new IllegalStateException("You own too many/not enough utilities");
            }

        } else {
            throw new IllegalAccessException("No-one owns " + name);
        }

    }
}
