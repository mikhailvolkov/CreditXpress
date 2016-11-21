package com.mikhail.creditexpress;
/**
 * @author Volkov Mikhail
 */
// XML node keys
public enum NodeKey {
    KEY_ROOT_TAG("credit"), // parent node
    KEY_ICON ("icon"),
    KEY_TIME("time"),
    KEY_SUMM("summ"),
    KEY_METHOD("method"),
    KEY_PERCENT("percent"),
    KEY_TIME_OF_CONSIDERATION("timeOfConsideration"),
    KEY_LINK("link");
    private final String key;

    private NodeKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
