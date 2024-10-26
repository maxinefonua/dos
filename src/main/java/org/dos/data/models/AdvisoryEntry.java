package org.dos.data.models;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class AdvisoryEntry {
    public String name;
    public List<String> countryTags;
    public ThreatLevel threatLevel;
    public String url;
    public ZonedDateTime updated;
    public ZonedDateTime published;

    public AdvisoryEntry(String name, List<String> countryTags, ThreatLevel threatLevel, String url, String updated, String published) {
        this.name = name;
        this.countryTags = countryTags;
        this.threatLevel = threatLevel;
        this.url = url;
        this.updated = Instant.parse(updated).atZone(ZoneId.of("UTC"));
        this.published = Instant.parse(published).atZone(ZoneId.of("UTC"));
    }

    @Override
    public String toString() {
        return "AdvisoryEntry{" +
                "name='" + name + '\'' +
                ", countryTags=" + countryTags.toString() +
                ", threatLevel=" + threatLevel +
                ", url='" + url + '\'' +
                ", updated=" + updated +
                ", published=" + published +
                '}';
    }
}
