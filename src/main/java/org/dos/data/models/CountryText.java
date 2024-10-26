package org.dos.data.models;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;
public class CountryText {
    public String tag;
    private Map<String,String> properties;

    public CountryText() {
        properties = new HashMap<>();
    }
    public CountryText(String tag) {
        this.tag = tag;
        properties = new HashMap<>();
    }
    @JsonAnySetter
    public void add(String key, String value) {
        properties.put(key,value);
    }

    @JsonAnyGetter
    public Map<String,String> getProperties() {
        return properties;
    }
}
