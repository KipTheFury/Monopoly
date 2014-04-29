/**
 * 
 */
package com.kb.monopoly.board.json;

import org.junit.Before;
import org.junit.Test;

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

        loader.loadBoard(json);
    }
}
