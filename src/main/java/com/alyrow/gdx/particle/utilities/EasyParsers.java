package com.alyrow.gdx.particle.utilities;

import com.badlogic.gdx.utils.Json;

public class EasyParsers {

    public static boolean parse(String cnt, boolean def) {
        return Try.ignore(() -> Boolean.parseBoolean(cnt), def);
    }

    public static byte parse(String cnt, byte def) {
        return Try.ignore(() -> Byte.parseByte(cnt), def);
    }

    public static char parse(String cnt, char def) {
        return Try.ignore(() -> cnt.charAt(0), def);
    }

    public static short parse(String cnt, short def) {
        return Try.ignore(() -> Short.parseShort(cnt), def);
    }

    public static int parse(String cnt, int def) {
        return Try.ignore(() -> Integer.parseInt(cnt), def);
    }

    public static float parse(String cnt, float def) {
        return Try.ignore(() -> Float.parseFloat(cnt), def);
    }

    public static long parse(String cnt, long def) {
        return Try.ignore(() -> Long.parseLong(cnt), def);
    }

    public static double parse(String cnt, double def) {
        return Try.ignore(() -> Double.parseDouble(cnt), def);
    }

    public static String parse(String cnt, String def) {
        if (cnt == null) return def;
        return cnt;
    }

    public static Json json = new Json();

    public static <Type> Type parse(String cnt, Class<Type> clazz, Type def) {
        return Try.ignore(() -> json.fromJson(clazz, cnt), def);
    }

}
