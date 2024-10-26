package org.dos.data.parsers;

import org.dos.data.models.AdvisoryEntry;
import org.dos.data.models.ThreatLevel;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
public class XMLParser {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final Set<String> PROPS;
    public final String TRAVEL_ADVISORY_URL = "https://travel.state.gov/content/travel/en/traveladvisories/traveladvisories.html";
    public final String TRAVEL_ADVISORY_FILEPATH = "src/main/resources/traveladvisory.xml";
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
    public List<AdvisoryEntry> parse(File file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);
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
                    case "published":
                        published = attr.getFirstChild().getTextContent();
                        break;
                    case "updated":
                        updated = attr.getFirstChild().getTextContent();
                        break;
                    case "title":
                        title = attr.getFirstChild().getTextContent().split("-")[0].trim();
                        break;
                    case "category":
                        String key = attr.getAttributes().getNamedItem("label").getTextContent();
                        String val = attr.getAttributes().getNamedItem("term").getTextContent();
                        if (val.isEmpty()) break;
                        if (key.equals("Country-Tag")) tags.add(val);
                        else if (key.equals("Threat-Level")) {
                            threat = ThreatLevel.getEnum(val);
                        }
                        break;
                    case "id":
                        id = attr.getFirstChild().getTextContent();
                        break;
                }
            }
            AdvisoryEntry advisoryEntry = new AdvisoryEntry(title,tags,threat,id,updated,published);
            LOGGER.info(advisoryEntry.toString());
            advisoryEntryList.add(advisoryEntry);
        }
        return advisoryEntryList;
    }
}
