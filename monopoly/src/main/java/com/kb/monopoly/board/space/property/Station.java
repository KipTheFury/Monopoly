/**
 * com.kb.monopoly.board.Station.java
 *
 * Created - 25 Apr 2014
 * Last Updated - 25 Apr 2014
 */
package com.kb.monopoly.board.space.property;

/**
 * @author Kyle
 * 
 */
public class Station extends Property
{

    private static final int STATION_VALUE = 200;
    private static final int STATION_RENT[] = { 0, 25, 50, 100, 200 };

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

        return STATION_RENT[stationCount];
    }
}
