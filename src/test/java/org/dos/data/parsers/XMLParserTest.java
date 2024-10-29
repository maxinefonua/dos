package org.dos.data.parsers;

import org.dos.data.models.AdvisoryEntry;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class XMLParserTest {

    XMLParser xmlParser;

    @Before
    public void setup() {
        xmlParser = new XMLParser();
    }

    @Test
    public void testParse() throws ParserConfigurationException, IOException, SAXException {
        List<AdvisoryEntry> advisoryEntryList = xmlParser.parse(new File("src/test/resources/traveladvisory.xml"));
        Assert.assertEquals(advisoryEntryList.size(),212);
    }

    @Test
    public void testParseThrowsExceptions() {
        try {
            xmlParser.parse(new File("fakepath"));
        } catch (Exception e) {
           Assert.assertEquals(e.getClass(),FileNotFoundException.class);
        }

        try {
            xmlParser.parse(new File("src/test/resources/malformed.xml"));
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), SAXParseException.class);
        }
    }
}
