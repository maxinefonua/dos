package org.dos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dos.data.parsers.XMLParser;

import java.io.File;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String TRAVEL_ADVISORY_URL = "https://travel.state.gov/content/travel/en/traveladvisories/traveladvisories.html";
    private static final String TRAVEL_ADVISORY_FILEPATH = "src/main/resources/traveladvisory.xml";
    public static void main(String[] args) throws Exception {
        LOGGER.info("Project setup");
        XMLParser xmlParser = new XMLParser();
        try {
            xmlParser.parse(new File(TRAVEL_ADVISORY_FILEPATH));
        } catch (Exception e) {
            LOGGER.error("failed parsing file '{}'",TRAVEL_ADVISORY_FILEPATH,e);
            throw new Exception("failed parsing file " + TRAVEL_ADVISORY_FILEPATH, e);
        }
    }
}