package com.mycompany.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;


public class JsonConverter {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static String toJson(Object obj){
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> classOfT){
        return gson.fromJson(json, classOfT);
    }

    public static <T> T fromJson(String json, Type typeOfT){
        return gson.fromJson(json, typeOfT);
    }
}
