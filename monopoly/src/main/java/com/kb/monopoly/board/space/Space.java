/**
 * com.kb.monopoly.board.Space.java
 *
 * Created - 22 Apr 2014
 * Last Updated - 22 Apr 2014
 */
package com.kb.monopoly.board.space;

/**
 * @author Kyle
 * 
 */
public class Space
{

    private final String name;

    /**
     * Constructor.
     * 
     * @param name
     *            - name of the space;
     */
    public Space(final String name)
    {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return name;
    }

}
