package com.usmp.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TimeZone;

public class JsonHelper {

    private final Gson gson;
    private final ObjectMapper objectMapper;

    private static DateFormat dateFormat;

    private static final TimeZone timeZone = TimeZone.getTimeZone("America/Lima");
    private static final JsonHelper INSTANCE = new JsonHelper();

    private JsonHelper() {
        gson = new GsonBuilder().create();
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }

    public static JsonHelper getInstance() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(timeZone);
        INSTANCE.objectMapper.setDateFormat(dateFormat);
        return INSTANCE;
    }

    public <T> T fromString(final String src, final Class<T> toValueType) {
        Map map = this.gson.fromJson(src, Map.class);
        return this.fromMap(map, toValueType);
    }

    public Map fromString(final String src) {
        return this.gson.fromJson(src, Map.class);
    }

    public <T> T fromMap(final Map src, final Class<T> toValueType) {
        return this.objectMapper.convertValue(src, toValueType);
    }

}
