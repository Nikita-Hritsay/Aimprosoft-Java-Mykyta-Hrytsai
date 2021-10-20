package com.aimprosoft.aimlearning.utils;

public class NumberUtils {

    public static Integer getInt(String num){
        if (num != null) {
            try {
                return Integer.valueOf(num);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public static Double getDouble(String num){
        if (num != null) {
            try {
                return Double.valueOf(num);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

}