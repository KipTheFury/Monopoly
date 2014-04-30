package com.kb.monopoly.board;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author kbennett
 * 
 */
public class PropertySets {

    public enum SetColour {

        Brown,
        LightBlue,
        Magenta,
        Orange,
        Red,
        Yellow,
        Green,
        DarkBlue;

    }

    private static HashMap<SetColour, ArrayList<Street>> sets = new HashMap<SetColour, ArrayList<Street>>();

    /**
     * Get an ArrayList of streets for a colour set.
     * 
     * @param colour
     *            the set colour.
     * @return - the streets in that set.
     */
    public static ArrayList<Street> getPropertySet(SetColour colour) {

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
    public static void addPropertySet(SetColour colour, ArrayList<Street> streets) {
        sets.put(colour, streets);
    }
}
