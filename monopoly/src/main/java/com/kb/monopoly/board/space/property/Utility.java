/**
 * com.kb.monopoly.board.Utility.java
 *
 * Created - 25 Apr 2014
 * Last Updated - 25 Apr 2014
 */
package com.kb.monopoly.board.space.property;

/**
 * @author Kyle
 * 
 */
public class Utility extends Property
{

    private static final int TWO_UTILITIES = 10;
    private static final int ONE_UTILITY = 4;
    private static final int UTILITY_VALUE = 150;

    /**
     * Constructor.
     * 
     * @param name
     */
    public Utility(final String name)
    {
        super(name, UTILITY_VALUE);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.kb.monopoly.board.Ownable#calculateRent()
     */
    @Override
    public int calculateRent() throws IllegalAccessException
    {
        throw new UnsupportedOperationException("Rent Calculation for Utilities requires a dice roll");
    }

    /**
     * @param diceRoll
     * @return
     */
    public int calculateRent(final int diceRoll) throws IllegalAccessException
    {

        if (getOwnedBy() != null)
        {

            int utilityCount = 0;

            for (final Property o : getOwnedBy().getPortfolio().getProperties())
            {
                if (o instanceof Utility)
                {
                    utilityCount++;
                }
            }

            switch (utilityCount)
            {
                case 1:
                    return ONE_UTILITY * diceRoll;
                case 2:
                    return TWO_UTILITIES * diceRoll;

                default:
                    throw new IllegalStateException("You own too many/not enough utilities");
            }

        }
        else
        {
            throw new IllegalAccessException("No-one owns " + getName());
        }

    }
}
