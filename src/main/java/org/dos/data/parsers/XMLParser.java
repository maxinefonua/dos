package org.dos.data.parsers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dos.data.models.AdvisoryEntry;
import org.dos.data.models.ThreatLevel;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class XMLParser {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final Set<String> PROPS;
    // TODO: return table of country entries

    static {
        Set<String> temp = new HashSet<>();
        temp.add("title");
        temp.add("id");
        temp.add("category");
        temp.add("published");
        temp.add("updated");

        PROPS = Set.copyOf(temp); // Java 9+
    }

    public List<AdvisoryEntry> parse(String xmlString) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = factory.newDocumentBuilder();
        InputSource inStream = new InputSource();
        inStream.setCharacterStream(new StringReader(xmlString));
        Document doc = db.parse(inStream);
        return extractEntries(doc);
    }

    private List<AdvisoryEntry> extractEntries(Document doc) {
        NodeList updatedList = doc.getElementsByTagName("updated");
        LOGGER.info("Successfully retrieved XML Doc, last updated: {}",updatedList.item(0).getFirstChild().getTextContent());
        NodeList entries = doc.getElementsByTagName("entry");
        List<AdvisoryEntry> advisoryEntryList = new ArrayList<>();
        for (int i = 0; i < entries.getLength(); i++) {
            Node entry = entries.item(i);
            NodeList childNodes = entry.getChildNodes();
            String title = "",published = "",updated = "",id = "";
            List<String> tags = new ArrayList<>();
            ThreatLevel threat = ThreatLevel.L1;
            for (int j = 0; j < childNodes.getLength(); j++) {
                Node attr = childNodes.item(j);
                String attrName = attr.getNodeName();
                if (!PROPS.contains(attrName)) continue;
                switch (attrName) {
                    case "published" -> published = attr.getFirstChild().getTextContent();
                    case "updated" -> updated = attr.getFirstChild().getTextContent();
                    case "title" -> title = attr.getFirstChild().getTextContent().split("-")[0].trim();
                    case "category" -> {
                        String key = attr.getAttributes().getNamedItem("label").getTextContent();
                        String val = attr.getAttributes().getNamedItem("term").getTextContent();
                        if (val.isEmpty()) break;
                        if (key.equals("Country-Tag")) tags.add(val);
                        else if (key.equals("Threat-Level")) threat = ThreatLevel.getEnum(val);
                    }
                    case "id" -> id = attr.getFirstChild().getTextContent();
                }
            }
            AdvisoryEntry advisoryEntry = new AdvisoryEntry(title,tags,threat,id,updated,published);
            LOGGER.debug(advisoryEntry);
            advisoryEntryList.add(advisoryEntry);
        }
        return advisoryEntryList;
    }

    public List<AdvisoryEntry> parse(File file) throws ParserConfigurationException, IOException, SAXException {
        LOGGER.info("parsing file '{}'",file.getName());
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);
        return extractEntries(doc);
    }
}
