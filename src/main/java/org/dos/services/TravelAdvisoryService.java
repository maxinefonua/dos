package org.dos.services;

import org.dos.data.models.AdvisoryEntry;

import java.util.List;

public interface TravelAdvisoryService {
    public List<AdvisoryEntry> getLatestTravelAdvisories();
}
