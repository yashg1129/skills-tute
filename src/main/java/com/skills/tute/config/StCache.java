package com.skills.tute.config;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class StCache {

    private static Map<String, Boolean> touchedTopics = new Hashtable<>();

    public static void put(String topic) {
        touchedTopics.put(topic, true);
    }

    public static void clear(String topic) {
        touchedTopics.remove(topic);
    }

    public static Set<Map.Entry<String, Boolean>> getTouchedTopics() {
        return touchedTopics.entrySet();
    }

    public static Map<String, Boolean> getTouchedTopics2() {
        return touchedTopics;
    }


}
