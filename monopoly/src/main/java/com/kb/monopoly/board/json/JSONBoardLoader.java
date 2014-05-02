/**
 * 
 */
package com.kb.monopoly.board.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.kb.monopoly.board.BoardLoader;
import com.kb.monopoly.board.Property;
import com.kb.monopoly.board.PropertySets;
import com.kb.monopoly.board.PropertySets.SetColour;
import com.kb.monopoly.board.Station;
import com.kb.monopoly.board.Street;
import com.kb.monopoly.board.Utility;

/**
 * @author kbennett
 * 
 */
public class JSONBoardLoader implements BoardLoader {

    private static final String VALUE = "-Value";
    private static final String BOARD_INDEX = "-BoardIndex";
    private static final String RENT_LEVELS = "RentLevels";
    private static final String SET_COLOUR = "-Colour";
    private static final String NAME = "-Name";
    private static final String STREET = "Street";
    private static final String UTILITY = "Utility";
    private static final String STATION = "Station";
    private static final String PROPERTY_SET = "PropertySet";

    private static final Logger LOG = Logger.getLogger(JSONBoardLoader.class);

    /*
     * (non-Javadoc)
     * 
     * @see com.kb.monopoly.board.BoardLoader#loadBoard(java.lang.String)
     */
    @Override
    public Collection<Property> loadProperties(final String fileLocation) {

        final ArrayList<Property> properties = new ArrayList<Property>();

        LOG.info("Loading board from [" + fileLocation + "]");

        final JSONParser parser = new JSONParser();
        JSONObject json;

        try {
            json = (JSONObject) parser.parse(new FileReader(fileLocation));

            final JSONArray propertySets = (JSONArray) json.get(PROPERTY_SET);
            loadPropertySets(propertySets);

            final JSONArray stations = (JSONArray) json.get(STATION);
            loadStations(stations);

            final JSONArray utilities = (JSONArray) json.get(UTILITY);
            loadUtilities(utilities);

        } catch (final FileNotFoundException e) {
            LOG.error(e.getMessage(), e);
        } catch (final IOException e) {
            LOG.error(e.getMessage(), e);
        } catch (final ParseException e) {
            LOG.error(e.getMessage(), e);
        }

        return properties;
    }

    @SuppressWarnings("unchecked")
    private void loadPropertySets(final JSONArray propertySets) {

        LOG.info("Loading Property Sets...");

        final Iterator<JSONObject> iterator = propertySets.iterator();

        while (iterator.hasNext()) {

            final JSONObject set = iterator.next();

            final SetColour colour = SetColour.valueOf((String) set.get(SET_COLOUR));

            final JSONArray streets = (JSONArray) set.get(STREET);
            loadStreets(streets, colour);
        }
    }

    @SuppressWarnings("unchecked")
    private void loadStreets(final JSONArray streets, final SetColour colour) {

        LOG.info("Loading Streets in the " + colour + " set...");

        final Iterator<JSONObject> iterator = streets.iterator();

        final ArrayList<Street> streetsList = new ArrayList<Street>();

        while (iterator.hasNext()) {

            final JSONObject obj = iterator.next();
            final JSONObject rentLevels = (JSONObject) obj.get(RENT_LEVELS);

            final int[] rent = getRentLevels(rentLevels.values().toArray());

            final int boardIndex = Integer.valueOf((String) obj.get(BOARD_INDEX));

            // TODO - add building cost to JSON
            final Street street =
                    new Street((String) obj.get(NAME), Integer.valueOf((String) obj.get(VALUE)), colour, 0, rent);

            LOG.info("Created [" + boardIndex + "\t" + street + "]");
        }

        PropertySets.addPropertySet(colour, streetsList);
    }

    private int[] getRentLevels(final Object[] levels) {

        final int[] rent = new int[levels.length];

        for (int i = 0; i < levels.length; i++) {
            rent[i] = Integer.valueOf((String) levels[i]);
        }

        return rent;
    }

    // TODO - Board index for utilities and stations
    @SuppressWarnings("unchecked")
    private void loadUtilities(final JSONArray utilities) {

        LOG.info("Loading Utilities...");

        final Iterator<JSONObject> iterator = utilities.iterator();

        while (iterator.hasNext()) {
            final JSONObject obj = iterator.next();

            final Utility util = new Utility((String) obj.get(NAME));
            LOG.info("Created [" + util + "]");
        }
    }

    @SuppressWarnings("unchecked")
    private void loadStations(final JSONArray stations) {

        LOG.info("Loading Stations...");

        final Iterator<JSONObject> iterator = stations.iterator();

        while (iterator.hasNext()) {
            final JSONObject obj = iterator.next();

            final Station station = new Station((String) obj.get(NAME));
            LOG.info("Created [" + station + "]");
        }
    }
}
