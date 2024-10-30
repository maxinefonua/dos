package org.dos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dos.data.models.AdvisoryEntry;
import org.dos.services.TravelAdvisoryService;
import org.dos.services.impl.FetchTravelAdvisoryService;

import java.util.List;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger();
    private static TravelAdvisoryService travelAdvisoryService;
    public static void main(String[] args) throws Exception {
        LOGGER.info("Project setup");
        travelAdvisoryService = new FetchTravelAdvisoryService();
        List<AdvisoryEntry> entries = travelAdvisoryService.getLatestTravelAdvisories();
        LOGGER.info(entries.size());
    }
}