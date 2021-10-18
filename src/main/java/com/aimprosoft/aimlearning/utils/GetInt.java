package com.aimprosoft.aimlearning.utils;

public class GetInt {

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

}
