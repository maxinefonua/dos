package org.dos.config;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FetchTravelAdvisoryServiceConfigTest {

    final String URL = "URL";

    FetchTravelAdvisoryServiceConfig fetchTravelAdvisoryServiceConfig;

    @Before
    public void setup() {
        fetchTravelAdvisoryServiceConfig = new FetchTravelAdvisoryServiceConfig();
    }

    @Test
    public void testSettersAndGetters() {
        fetchTravelAdvisoryServiceConfig.setTravelAdvisoryUrl(URL);
        Assert.assertEquals(URL,fetchTravelAdvisoryServiceConfig.getTravelAdvisoryUrl());
    }
}
