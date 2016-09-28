package com.yuan.java.test.base;

import com.yuan.java.test.util.JsonUtil;

import java.util.List;

/**
 * Desc :
 * Author : chavezyuan
 * Date : 2016-08-01
 */
public class PolygonTest {

    /*
    {"type":1,"data":[
    {"lng":121.51672840118408,"lat":31.132717729618534},
    {"lng":121.52780055999756,"lat":31.134627895676257},
    {"lng":121.53140544891357,"lat":31.127464574623126},
    {"lng":121.5263843536377,"lat":31.122872417663654},
    {"lng":121.51934623718262,"lat":31.122982632033576},
    {"lng":121.51793003082275,"lat":31.125150155294353},
    {"lng":121.52119159698486,"lat":31.12834624332696},
    {"lng":121.51672840118408,"lat":31.132717729618534}]}
     */

    static String json = "{\"type\":1,\"data\":[{\"lng\":121.51672840118408,\"lat\":31.132717729618534},{\"lng\":121.52780055999756,\"lat\":31.134627895676257},{\"lng\":121.53140544891357,\"lat\":31.127464574623126},{\"lng\":121.5263843536377,\"lat\":31.122872417663654},{\"lng\":121.51934623718262,\"lat\":31.122982632033576},{\"lng\":121.51793003082275,\"lat\":31.125150155294353},{\"lng\":121.52119159698486,\"lat\":31.12834624332696},{\"lng\":121.51672840118408,\"lat\":31.132717729618534}]}";

    static Poi target = new Poi(31.2, 121.6);

    static int pos = 0;

    public static void main(String[] args) {

        FencesLocationPoly poly = JsonUtil.fromJson(json, FencesLocationPoly.class);

        List<Poi> pois = poly.data;

        /**
         *
         * 1. 获取bound box
         *    判断target是否在bound box内
         * 2. target 向左引一条射线
         *    判断顶点是否在边上
         * 3. 判断交点
         *    如果target的从坐标与P1,P2中较小的纵坐标相同，则直接忽略这种情况
         *
         */

//        List<Poi> boundBox = getBoundBox(pois);




    }










    class FencesLocation {
        public int type;
    }

    class FencesLocationPoly extends FencesLocation {
        public List<Poi> data;
    }

    static class Poi {
        public double lat;
        public double lng;

        public Poi() {}
        public Poi(double lat, double lng) {
            this.lat = lat;
            this.lng = lng;
        }
    }


}
