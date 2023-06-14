package com.accolite.util;

import java.util.HashMap;
import java.util.Map;

public class GlobalData<T> {

    Map<String, String> map = new HashMap<>();
    Map<String, Object> genericData = new HashMap<>();

    private static GlobalData instance;

    private GlobalData() {
    }

    public static GlobalData getInstance() {
        if (instance == null) {
            instance = new GlobalData();
        }
        return instance;
    }

    public void setEnvironmentData(final String key,
                                   final String value) {
        map.put(key, value);
    }

    public void setEnvironmentData(final String key,
                                   final Object t) {
        genericData.put(key, t);
    }

    public String getData(final String key) {
        return map.get(key);
    }

    public T getEnvironmentData(final String key) {
        return (T)genericData.get(key);
    }
}
