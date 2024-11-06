package org.dos.services.impl;

import org.dos.config.FetchTravelAdvisoryServiceConfig;
import org.dos.data.models.AdvisoryEntry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXParseException;

import java.net.ConnectException;
import java.util.List;

public class FetchTravelAdvisoryServiceTest {
    private static final String JSON_FETCH_URL = "https://cadatacatalog.state.gov/dataset/4a387c35-29cb-4902-b91d-3da0dc02e4b2/resource/299b3b67-3c09-46a3-9eb7-9d0086581bcb/download/countrytravelinfo.json";
    private static final String INCORRECT_URL = "http://test.test/";
    FetchTravelAdvisoryService fetchTravelAdvisoryService;
    FetchTravelAdvisoryServiceConfig fetchTravelAdvisoryServiceConfig;

    @Before
    public void setup() {
        fetchTravelAdvisoryServiceConfig = new FetchTravelAdvisoryServiceConfig();
        fetchTravelAdvisoryService = new FetchTravelAdvisoryService(fetchTravelAdvisoryServiceConfig);
    }

    // TODO: test constructor missing config file
    @Test
    public void testConstructor() {
        Assert.assertNotNull(fetchTravelAdvisoryService);
    }
    @Test
    public void testGetAdvisories() {
        List<AdvisoryEntry> advisoryEntryList = fetchTravelAdvisoryService.getLatestTravelAdvisories();
        Assert.assertNotNull(advisoryEntryList);
        Assert.assertNotEquals(0,advisoryEntryList.size());
    }


    @Test
    public void testGetAdvisoriesJsonUrl() {
        fetchTravelAdvisoryServiceConfig.setTravelAdvisoryUrl(JSON_FETCH_URL);
        fetchTravelAdvisoryService = new FetchTravelAdvisoryService(fetchTravelAdvisoryServiceConfig);
        try {
            fetchTravelAdvisoryService.getLatestTravelAdvisories();
        } catch (Exception e) {
            Assert.assertEquals(SAXParseException.class,e.getCause().getClass());
        }
    }

    @Test
    public void testGetAdvisoriesIncorrectUrl() {
        fetchTravelAdvisoryServiceConfig.setTravelAdvisoryUrl(INCORRECT_URL);
        fetchTravelAdvisoryService = new FetchTravelAdvisoryService(fetchTravelAdvisoryServiceConfig);
        try {
            fetchTravelAdvisoryService.getLatestTravelAdvisories();
        } catch (Exception e) {
            Assert.assertEquals(ConnectException.class,e.getCause().getClass());
        }
    }
}
