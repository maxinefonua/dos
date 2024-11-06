package org.dos.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dos.config.FetchTravelAdvisoryServiceConfig;
import org.dos.data.models.AdvisoryEntry;
import org.dos.data.parsers.XMLParser;
import org.dos.services.TravelAdvisoryService;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class FetchTravelAdvisoryService implements TravelAdvisoryService {
    private static final Logger LOGGER = LogManager.getLogger();
    private final FetchTravelAdvisoryServiceConfig fetchTravelAdvisoryServiceConfig;
    private static final HttpClient catalogClient = HttpClient.newHttpClient();
    private final XMLParser xmlParser = new XMLParser();

    public FetchTravelAdvisoryService(FetchTravelAdvisoryServiceConfig fetchTravelAdvisoryServiceConfig) {
        this.fetchTravelAdvisoryServiceConfig = fetchTravelAdvisoryServiceConfig;
    }

    // TODO: implement caching ?
    @Override
    public List<AdvisoryEntry> getLatestTravelAdvisories() {

        HttpResponse response = fetchResponseFromClient();
        try {
            return xmlParser.parse(response.body().toString());
        } catch (Exception e) {
            LOGGER.error("failed to parse response '{}'",response,e);
            throw new RuntimeException("failed to parse response " + response,e);
        }
    }

    private HttpResponse fetchResponseFromClient() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(fetchTravelAdvisoryServiceConfig.getTravelAdvisoryUrl()))
                .build();
        LOGGER.info("sending request '{}'", request);
        try {
            return catalogClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            LOGGER.error("failed to send request '{}'",request,e);
            throw new RuntimeException("failed to send request " + request,e);
        }
    }


}
