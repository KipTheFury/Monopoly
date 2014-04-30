/**
 * com.kb.monopoly.board.xml.XMLBoardLoader.java
 *
 * Created - 28 Apr 2014
 * Last Updated - 28 Apr 2014
 */
package com.kb.monopoly.board;

import java.util.Collection;

/**
 * @author Kyle
 * 
 */
public interface BoardLoader {

    public Collection<Property> loadProperties(String fileLocation) throws Exception;

}
