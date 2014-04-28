/**
 * com.kb.monopoly.board.xml.XMLBoardLoader.java
 *
 * Created - 28 Apr 2014
 * Last Updated - 28 Apr 2014
 */
package com.kb.monopoly.board.xml;


/**
 * @author Kyle
 * 
 */
public interface XMLBoardLoader {

    static final String PROPERTIES = "Properties";
    static final String PROPERTY_SET = "PropertySet";
    static final String STREET = "Street";

    public void loadBoardFromXML(String fileLocation);
}
