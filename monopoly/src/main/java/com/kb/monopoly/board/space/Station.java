/**
 * com.kb.monopoly.board.Station.java
 *
 * Created - 25 Apr 2014
 * Last Updated - 25 Apr 2014
 */
package com.kb.monopoly.board.space;

/**
 * @author Kyle
 * 
 */
public class Station extends Property
{

    private static final int STATION_VALUE = 200;

    /**
     * Constructor.
     * 
     * @param name
     */
    public Station(final String name)
    {
        super(name, STATION_VALUE);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.kb.monopoly.board.Ownable#calculateRent()
     */
    @Override
    public int calculateRent() throws IllegalAccessException
    {

        int stationCount = 0;

        if (getOwnedBy() != null)
        {
            for (final Property o : getOwner().getPortfolio().getProperties())
            {
                if (o instanceof Station)
                {
                    stationCount++;
                }
            }
        }
        else
        {
            throw new IllegalAccessException("No-one owns this property");
        }

        switch (stationCount)
        {
            case 1:
                return 25;
            case 2:
                return 50;
            case 3:
                return 100;
            case 4:
                return 200;
            default:
                throw new IllegalStateException("You own too many/not enough stations");
        }
    }
}
