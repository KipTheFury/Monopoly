/**
 * com.kb.monopoly.board.xml.XMLBoardLoader.java
 *
 * Created - 28 Apr 2014
 * Last Updated - 28 Apr 2014
 */
package com.kb.monopoly.board;

import java.util.HashMap;

import com.kb.monopoly.board.space.Property;

/**
 * @author Kyle
 * 
 */
public interface BoardLoader {

    HashMap<Integer, Property> loadProperties(String fileLocation);

}