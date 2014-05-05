/**
 * com.kb.monopoly.board.xml.XMLBoardValidatorTest.java
 *
 * Created - 28 Apr 2014
 * Last Updated - 28 Apr 2014
 */
package com.kb.monopoly.board.xml;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Kyle
 * 
 */
public class XMLBoardValidatorTest {

    private static final String xml = "src/test/resources/DefaultBoard.xml";
    private static final String invalid_xml = "src/test/resources/InvalidBoard.xml";
    private static final String xsd = "src/test/resources/monopoly.xsd";
    private XMLBoardValidator validator;

    @Before
    public void setUp() throws Exception {
        validator = new XMLBoardValidator();
    }

    @Test
    public void canValidateXMl_valid() throws Exception {
        assertTrue(validator.validate(new File(xml), new File(xsd)));
    }

    @Test
    public void canValidateXMl_invalid() throws Exception {
        assertFalse(validator.validate(new File(invalid_xml), new File(xsd)));
    }
}
