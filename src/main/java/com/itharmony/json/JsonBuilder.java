package com.itharmony.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

/**
 * Class which converts a java map to JSON
 *
 * @return Map in JSON form
 *
 */

public final class JsonBuilder {
    public static String mapToJson (Map map) {
        Gson gson = new Gson();
        return gson.toJson(map);
    }
}
