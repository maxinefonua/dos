package org.dos.data.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Collections;

public class AdvisoryEntryTest {
    AdvisoryEntry advisoryEntry;
    String name = "Tonga";
    String tag = "TN";
    ThreatLevel threatLevel = ThreatLevel.L1;
    String url = "https://travel.state.gov/content/travel/en/traveladvisories/traveladvisories/tonga-travel-advisory.html";
    String updated = "2023-07-24T00:00:00.000Z";
    String published = "2023-07-24T16:22:44.702Z";

    @Before
    public void setup() {
        advisoryEntry = new AdvisoryEntry(name, Collections.singletonList(tag),threatLevel,url,updated,published);
    }

    @Test
    public void testConstructor() {
        Assert.assertEquals(advisoryEntry.name,name);
        Assert.assertEquals(advisoryEntry.countryTags.get(0),tag);
        Assert.assertEquals(advisoryEntry.threatLevel,threatLevel);
        Assert.assertEquals(advisoryEntry.url,url);
        Assert.assertEquals(advisoryEntry.updated,Instant.parse(updated).atZone(ZoneId.of("UTC")));
        Assert.assertEquals(advisoryEntry.published,Instant.parse(published).atZone(ZoneId.of("UTC")));
    }

    @Test
    public void testToString() {
        String expected = "AdvisoryEntry{name='Tonga', countryTags=[TN], threatLevel=Level 1: Exercise Normal Precautions, url='https://travel.state.gov/content/travel/en/traveladvisories/traveladvisories/tonga-travel-advisory.html', updated=2023-07-24T00:00Z[UTC], published=2023-07-24T16:22:44.702Z[UTC]}";
        String toString = advisoryEntry.toString();
        Assert.assertEquals(expected,toString);
    }
}
