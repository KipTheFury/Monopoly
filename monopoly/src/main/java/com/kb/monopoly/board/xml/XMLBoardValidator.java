/**
 * com.kb.monopoly.board.xml.XMLBoardValidator.java
 *
 * Created - 28 Apr 2014
 * Last Updated - 28 Apr 2014
 */
package com.kb.monopoly.board.xml;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.log4j.Logger;

/**
 * @author Kyle
 * 
 */
public class XMLBoardValidator {

    private static final Logger LOG = Logger.getLogger(XMLBoardValidator.class);

    public boolean validate(File xml, File xsd) {

        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsd);

            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xml));

            return true;

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return false;
        }

    }
}
