package org.dos.data.parsers;

import org.dos.data.models.AdvisoryEntry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class XMLParserTest {
    XMLParser xmlParser;
    private static final String TEST_FILE_PATH = "src/test/resources/traveladvisory.xml";

    @Before
    public void setup() {
        xmlParser = new XMLParser();
    }

    @Test
    public void testParseFile() throws ParserConfigurationException, IOException, SAXException {
        List<AdvisoryEntry> advisoryEntryList = xmlParser.parse(new File(TEST_FILE_PATH));
        Assert.assertEquals(advisoryEntryList.size(),212);
    }

    @Test
    public void testParseString() throws ParserConfigurationException, IOException, SAXException {
        String xmlString = new String(Files.readAllBytes(Paths.get(TEST_FILE_PATH)));

        List<AdvisoryEntry> advisoryEntryList = xmlParser.parse(xmlString);
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
