/**
 * 
 */
package com.kb.monopoly.board.json;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.kb.monopoly.board.PropertySets;
import com.kb.monopoly.board.PropertySets.SetColour;
import com.kb.monopoly.board.space.Property;

/**
 * @author kbennett
 * 
 */
public class JSONBoardLoaderTest {

    private static final String json = "src/test/resources/DefaultBoard.json";

    private JSONBoardLoader loader;

    @Before
    public void setUp() {

        loader = new JSONBoardLoader();
    }

    @Test
    public void canLoadBoard_JSON() throws Exception {

        final HashMap<Integer, Property> properties = loader.loadProperties(json);

        assertTrue(properties.size() == 28);

        for (final SetColour colour : SetColour.values()) {
            assertNotNull(PropertySets.getPropertySet(colour));
            assertFalse(PropertySets.getPropertySet(colour).isEmpty());
        }
    }
}
