package org.dos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dos.config.FetchTravelAdvisoryServiceConfig;
import org.dos.data.models.AdvisoryEntry;
import org.dos.services.TravelAdvisoryService;
import org.dos.services.impl.FetchTravelAdvisoryService;

import java.util.List;

public class Main {

    // TODO: have fetch running service as a fetch entries services
        // pulls entries from local DB
        // if local DB empty -> fetch from URL
        // if local DB has entries -> fetches from URL if older than a day
        // publishes NEW and UPDATED to a kafka pipeline
    // TODO: build event processor as a running service listening to kafka pipeline
        // consumes NEW and UPDATED and updates DB
        // creates/updates entries for country tags -> has a geoarea field for Oceania/Asia/etc.
        // creates/updates entry for country travel advisory
    // TODO: running API service to display most recently updated entries
        // calls fetch advisories (to get most updated)
        // local API displaying all content of entries
    // TODO: basic UI that displays entries, allows for filtering, allows refreshing

    private static final Logger LOGGER = LogManager.getLogger();
    private static TravelAdvisoryService travelAdvisoryService;
    public static void main(String[] args) throws Exception {
        LOGGER.debug("starting up project");
        travelAdvisoryService = new FetchTravelAdvisoryService(new FetchTravelAdvisoryServiceConfig());
        List<AdvisoryEntry> entries = travelAdvisoryService.getLatestTravelAdvisories();
        LOGGER.info(entries.size());
    }
}