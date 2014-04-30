/**
 * 
 */
package com.kb.monopoly.board.json;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.kb.monopoly.board.BoardLoader;
import com.kb.monopoly.board.Property;
import com.kb.monopoly.board.PropertySets;
import com.kb.monopoly.board.PropertySets.SetColour;
import com.kb.monopoly.board.Street;

/**
 * @author kbennett
 * 
 */
public class JSONBoardLoader implements BoardLoader {

    private static final String STREET = "Street";
    private static final String UTILITY = "Utility";
    private static final String STATION = "Station";
    private static final String PROPERTY_SET = "PropertySet";

    private static final Logger log = Logger.getLogger(JSONBoardLoader.class);

    /*
     * (non-Javadoc)
     * 
     * @see com.kb.monopoly.board.BoardLoader#loadBoard(java.lang.String)
     */
    @Override
    public Collection<Property> loadProperties(String fileLocation) throws Exception {

        ArrayList<Property> properties = new ArrayList<Property>();

        log.info("Loading board from [" + fileLocation + "]");

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(new FileReader(fileLocation));

        JSONArray propertySets = (JSONArray) json.get(PROPERTY_SET);
        loadPropertySets(propertySets);

        JSONArray stations = (JSONArray) json.get(STATION);
        loadStations(stations);

        JSONArray utilities = (JSONArray) json.get(UTILITY);
        loadUtilities(utilities);

        return properties;
    }

    @SuppressWarnings("unchecked")
    private void loadPropertySets(JSONArray propertySets) {

        log.info("Loading Property Sets...");

        Iterator<JSONObject> iterator = propertySets.iterator();

        while (iterator.hasNext()) {

            JSONObject set = iterator.next();

            SetColour colour = SetColour.valueOf((String) set.get("-Colour"));

            JSONArray streets = (JSONArray) set.get(STREET);
            loadStreets(streets, colour);
        }
    }

    @SuppressWarnings("unchecked")
    private void loadStreets(JSONArray streets, SetColour colour) {

        log.info("Loading Streets in the " + colour + " set...");

        Iterator<JSONObject> iterator = streets.iterator();

        ArrayList<Street> streetsList = new ArrayList<Street>();

        while (iterator.hasNext()) {

            JSONObject street = iterator.next();

            System.out.println(street);
        }

        PropertySets.addPropertySet(colour, streetsList);
    }

    @SuppressWarnings("unchecked")
    private void loadUtilities(JSONArray utilities) {

        log.info("Loading Utilities...");

        Iterator<JSONObject> iterator = utilities.iterator();

        while (iterator.hasNext()) {
            JSONObject obj = iterator.next();
            System.out.println(obj);
        }
    }

    @SuppressWarnings("unchecked")
    private void loadStations(JSONArray stations) {

        log.info("Loading Stations...");

        Iterator<JSONObject> iterator = stations.iterator();

        while (iterator.hasNext()) {
            JSONObject obj = iterator.next();
            System.out.println(obj);
        }
    }
}
