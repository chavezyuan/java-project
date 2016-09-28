package com.yuan.java.test.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author linlei
 * @date: 2016/5/5
 */
public class JsonUtil {
    private static Gson gson = new GsonBuilder().create();
    private static GsonBuilder gsonBuilder = new GsonBuilder();

    /**
     * 把含Date的json串转换成实体对象
     *
     * @param jsonObjStr
     * @param clazz
     * @param format     日期格式，例如"yyyy-MM-dd HH:mm:ss"
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String jsonObjStr, Class<T> clazz, String format) {
        Gson gson = gsonBuilder.setDateFormat(format).create();
        return gson.fromJson(jsonObjStr, clazz);
    }

    /**
     * 把json串转换成实体对象
     *
     * @param jsonObjStr
     * @return
     */
    public static <T> T fromJson(String jsonObjStr, Class<T> clazz) {
        return gson.fromJson(jsonObjStr, clazz);
    }

    /**
     * 把含Date的json串转换成实体对象列表
     *
     * @param jsonArrStr
     * @param clazz
     * @param format     日期格式，例如"yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static <T> List<T> fromJsonList(String jsonArrStr, Class<T> clazz, String format) {
        Type type = new TypeToken<ArrayList<JsonObject>>() {
        }.getType();
        ArrayList<JsonObject> jsonObjs = new Gson().fromJson(jsonArrStr, type);

        List<T> listOfT = new ArrayList<>();

        Gson gson = gsonBuilder.setDateFormat(format).create();
        for (JsonObject jsonObj : jsonObjs) {
            listOfT.add(gson.fromJson(jsonObj, clazz));
        }

        return listOfT;
    }

    /**
     * 把json串转换成实体对象列表
     *
     * @param jsonArrStr
     * @param clazz
     * @return
     */
    public static <T> List<T> fromJsonList(String jsonArrStr, Class<T> clazz) {
        Type type = new TypeToken<ArrayList<JsonObject>>() {
        }.getType();
        ArrayList<JsonObject> jsonObjs = gson.fromJson(jsonArrStr, type);

        List<T> listOfT = new ArrayList<>();
        for (JsonObject jsonObj : jsonObjs) {
            listOfT.add(gson.fromJson(jsonObj, clazz));
        }

        return listOfT;
    }

    /**
     * 把包含Date的实体对象转换成Json串
     *
     * @param obj
     * @param dateFormat 日期格式，例如"yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String toJson(Object obj, String dateFormat) {
        Gson gson = gsonBuilder.setDateFormat(dateFormat).create();
        return gson.toJson(obj);

    }

    /**
     * 从实体对象转换成json串
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static void main(String args[]){
        String json = "{\"productName\":\"sdfsf\",\"city\":\"4\",\"city_DDCity\":\"上海\",\"productType\":\"1\",\"productLevel\":\"1\",\"license\":\"2\",\"startTime\":\"2016-06-10\",\"endTime\":\"2016-06-22\",\"advancedAt\":\"27\",\"advancedAtUnit\":\"0\",\"minRentUnit\":\"0\",\"maxRentUnit\":\"0\",\"appName\":\"sdfsdf\",\"nameDesc\":\"sdfsf\",\"slogan\":\"sdf\",\"name\":\"sdfsf\",\"cityId\":\"4\",\"startDelivery\":\"2016-06-10\",\"endDelivery\":\"2016-06-22 23:59:59\",\"licenseTypeId\":\"2\",\"productTypeId\":\"1\",\"productLevelId\":\"1\"}";
        Object gsonObj = new GsonBuilder();
        String s = "1";
    }
}

