package org.dos.services.impl;

import org.junit.Assert;
import org.junit.Test;

public class FetchTravelAdvisoryServiceTest {
    FetchTravelAdvisoryService fetchTravelAdvisoryService;

    // TODO: test constructor missing config file
    @Test
    public void testConstructor() {
        fetchTravelAdvisoryService = new FetchTravelAdvisoryService();
        Assert.assertNotNull(fetchTravelAdvisoryService);
    }
}
