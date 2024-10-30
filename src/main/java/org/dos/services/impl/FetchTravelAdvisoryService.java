package org.dos.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dos.data.models.AdvisoryEntry;
import org.dos.data.parsers.XMLParser;
import org.dos.services.TravelAdvisoryService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class FetchTravelAdvisoryService implements TravelAdvisoryService {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String TRAVEL_ADVISORY_URL = "https://cadatacatalog.state.gov/dataset/4a387c35-29cb-4902-b91d-3da0dc02e4b2/resource/4c727464-8e6f-4536-b0a5-0a343dc6c7ff/download/traveladvisory.xml";
    private final HttpClient catalogClient;
    private final XMLParser xmlParser;

    public FetchTravelAdvisoryService() {
        catalogClient = HttpClient.newHttpClient();
        xmlParser = new XMLParser();
    }

    // TODO: implement caching
    @Override
    public List<AdvisoryEntry> getLatestTravelAdvisories() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TRAVEL_ADVISORY_URL))
                .build();
        try {
            HttpResponse response = catalogClient.send(request, HttpResponse.BodyHandlers.ofString());
            try {
                return xmlParser.parse(response.body().toString());
            } catch ( Exception e)  {
                LOGGER.error("failed to parse response '{}'",response,e);
                throw new RuntimeException("failed to parse response " + response,e);
            }
        } catch (Exception e) {
            LOGGER.error("failed to send request '{}'",request,e);
            throw new RuntimeException("failed to send request " + request,e);
        }
    }
}
