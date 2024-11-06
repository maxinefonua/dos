package org.dos.services;

import org.dos.data.models.AdvisoryEntry;

import java.util.List;

public interface TravelAdvisoryService {

//        private static final FetchTravelAdvisoryServiceConfig fetchTravelAdvisoryServiceConfig = new FetchTravelAdvisoryServiceConfig();
    List<AdvisoryEntry> getLatestTravelAdvisories();
}
