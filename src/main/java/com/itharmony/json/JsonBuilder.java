package com.itharmony.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

public final class JsonBuilder {

    public static String mapToJson (Map map) {

        Gson gson = new Gson();
        String json = gson.toJson(map);
        return json;
    }

}
