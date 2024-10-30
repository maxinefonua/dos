package org.dos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dos.data.models.AdvisoryEntry;
import org.dos.data.parsers.XMLParser;
import org.dos.services.TravelAdvisoryService;
import org.dos.services.impl.FetchTravelAdvisoryService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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