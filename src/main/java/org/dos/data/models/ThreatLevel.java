package org.dos.data.models;

public enum ThreatLevel {
    L1("Level 1: Exercise Normal Precautions"),
    L2("Level 2: Exercise Increased Caution"),
    L3("Level 3: Reconsider Travel"),
    L4("Level 4: Do Not Travel"),
    Other("Other");

    private final String text;

    ThreatLevel(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public static ThreatLevel getEnum(String text) {
        for(ThreatLevel v : values())
            if(v.text.equals(text)) return v;
        return ThreatLevel.Other;
    }
}
