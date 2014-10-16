package com.kb.monopoly.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kb.monopoly.board.space.Street;

/**
 * @author kbennett
 * 
 */
public final class PropertySets
{

    private PropertySets()
    {

    }

    /**
     * The different set colours.
     * 
     * @author kbennett
     * 
     */
    public enum SetColour
    {
        Brown, LightBlue, Magenta, Orange, Red, Yellow, Green, DarkBlue;
    }

    private static Map<SetColour, List<Street>> sets = new HashMap<SetColour, List<Street>>();

    /**
     * Get an ArrayList of streets for a colour set.
     * 
     * @param colour
     *            the set colour.
     * @return - the streets in that set.
     */
    public static List<Street> getPropertySet(final SetColour colour)
    {
        return sets.get(colour);
    }

    /**
     * Add a set of properties to the map.
     * 
     * @param colour
     *            - The set colour.
     * @param streets
     *            - the streets in that set.
     */
    public static void addPropertySet(final SetColour colour, final ArrayList<Street> streets)
    {
        sets.put(colour, streets);
    }
}
