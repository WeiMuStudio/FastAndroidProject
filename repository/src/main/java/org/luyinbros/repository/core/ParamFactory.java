package org.luyinbros.repository.core;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Description:
 * Created by Andy Hong on 10/19/2017.
 */

public class ParamFactory {
    private static Gson gson;

    static {
        gson = new Gson();
    }


    public static RequestBody jsonParamBody(Object param) {
        String json = gson.toJson(param);
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
    }

    public static String generationJson(Object param) {
        return gson.toJson(param);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
        return gson.fromJson(json, classOfT);
    }

    public static <T> T fromJson(String json, Type type) throws JsonSyntaxException {
        return gson.fromJson(json, type);
    }

}
