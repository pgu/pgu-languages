package com.pgu.server.utils;

public enum ConfigApp {
    FB_APP_KEY, FB_APP_SECRET, FB_CALLBACK_URL, //
    TWITTER_APP_KEY, TWITTER_APP_SECRET, //
    ADMIN_DATA;

    public String getValue() {
        return System.getProperty(toString());
    }
}
